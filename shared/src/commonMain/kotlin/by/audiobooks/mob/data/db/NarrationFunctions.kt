package by.audiobooks.mob.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import by.audiobooks.mob.domain.Language
import by.audiobooks.mob.domain.Narration
import by.audiobooks.mob.domain.NarrationDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.datetime.LocalDate
import kotlin.coroutines.CoroutineContext

internal fun AudiobooksByDB.getAllNarrations(context: CoroutineContext = Dispatchers.IO): Flow<List<Narration>> =
    narrationQueries.selectAllNarrations().asFlow().mapToList(context)
        .map { it.groupBy { record -> record.narrationUuid }
            .values
            .map { recordGroup -> convertSelectAllNarrationsRecordSetToNarration(recordGroup) }
        }

internal fun AudiobooksByDB.getNarrationsByBookUuid(bookUuid: String, context: CoroutineContext = Dispatchers.IO): Flow<List<Narration>> =
    narrationQueries.selectNarrationsByBookUuid(bookUuid).asFlow().mapToList(context)
        .map { it.groupBy { record -> record.narrationUuid }
            .values
            .map { recordSet -> convertRecordSetToNarration(recordSet) }
        }

internal fun AudiobooksByDB.getNarrationsByBookUuid(bookUuid: String): List<Narration> =
    narrationQueries.selectNarrationsByBookUuid(bookUuid).executeAsList()
        .groupBy { record -> record.narrationUuid }
        .map { it.value }
        .map { recordSet -> convertRecordSetToNarration(recordSet) }

internal fun AudiobooksByDB.getNarrationsWithDetailsByBookUuidSubscription(bookUuid: String, context: CoroutineContext = Dispatchers.IO): Flow<List<NarrationDetails>> =
    transformNarrationsFlowToNarrationsWithDetailsFlow(getNarrationsByBookUuid(bookUuid, context))

internal fun AudiobooksByDB.getNarrationsWithDetailsByBookUuid(bookUuid: String): List<NarrationDetails> =
    getNarrationsByBookUuid(bookUuid).map(::narrationToNarrationDetails)

@OptIn(ExperimentalCoroutinesApi::class)
private fun AudiobooksByDB.transformNarrationsFlowToNarrationsWithDetailsFlow(inboundFlow: Flow<List<Narration>>): Flow<List<NarrationDetails>> = flow {
    inboundFlow.collect {
        val transformedList = it.asFlow().flatMapMerge { narration ->
            transformNarrationToNarrationsWithDetails(narration)
        }.toList()
        emit(transformedList)
    }
}

private suspend fun AudiobooksByDB.transformNarrationToNarrationsWithDetails(narration: Narration): Flow<NarrationDetails> = flow {
    emit(narrationToNarrationDetails(narration))
}

private fun AudiobooksByDB.narrationToNarrationDetails(narration: Narration): NarrationDetails =
    NarrationDetails(
        uuid = narration.uuid,
        bookUuid = narration.bookUuid,
        duration = narration.duration,
        paid = narration.paid,
        language = narration.language,
        coverImage = narration.coverImage,
        coverImageSource = narration.coverImageSource,
        date = narration.date,
        description = narration.description,
        previewUrl = narration.previewUrl,
        links = getLinksDetailsByNarrationUuid(narrationUuid = narration.uuid),
        narrators = narration.narratorUuids.mapNotNull { getPersonByUuid(it) }.toList(),
        publishers = narration.publisherUuids.mapNotNull { getPublisherByUuid(it) }.toList(),
        translators = narration.translatorUuids.mapNotNull { getPersonByUuid(it) }.toList()
    )

private fun convertRecordSetToNarration(recordGroup: List<SelectNarrationsByBookUuid>): Narration =
    recordGroup.map { record ->
        Narration(
            uuid = record.narrationUuid,
            bookUuid = record.bookUuid,
            duration = record.narrationDuration,
            paid = record.narrationPaid.equals(true.toString(), true),
            language = Language.valueOf(record.narrationLanguage),
            coverImage = record.narrationCoverImage,
            coverImageSource = record.narrationCoverImageSource,
            date = LocalDate.parse(record.narrationAddedDate),
            description = record.narrationDescription,
            previewUrl =  record.narrationPreviewUrl,
            narratorUuids = recordGroup
                .filter { it.narratorPriority != null && it.narratorUuid != null }
                .sortedBy { it.narratorPriority }.map { it.narratorUuid!! }.distinct(),
            publisherUuids = recordGroup
                .filter { it.publisherPriority != null && it.publisherUuid != null }
                .sortedBy { it.publisherPriority }.map { it.publisherUuid!! }.distinct(),
            translatorUuids = recordGroup
                .filter { it.translatorPriority != null && it.translatorUuid != null }
                .sortedBy { it.translatorPriority }.map { it.translatorUuid!! }.distinct()
        )
    }.first()

private fun convertSelectAllNarrationsRecordSetToNarration(recordGroup: List<SelectAllNarrations>): Narration =
    recordGroup.map { record ->
        Narration(
            uuid = record.narrationUuid,
            bookUuid = record.bookUuid,
            duration = record.narrationDuration,
            paid = record.narrationPaid.equals(true.toString(), true),
            language = Language.valueOf(record.narrationLanguage),
            coverImage = record.narrationCoverImage,
            coverImageSource = record.narrationCoverImageSource,
            date = LocalDate.parse(record.narrationAddedDate),
            description = record.narrationDescription,
            previewUrl =  record.narrationPreviewUrl,
            narratorUuids = recordGroup
                .filter { it.narratorPriority != null && it.narratorUuid != null }
                .sortedBy { it.narratorPriority }.map { it.narratorUuid!! }.distinct(),
            publisherUuids = recordGroup
                .filter { it.publisherPriority != null && it.publisherUuid != null }
                .sortedBy { it.publisherPriority }.map { it.publisherUuid!! }.distinct(),
            translatorUuids = recordGroup
                .filter { it.translatorPriority != null && it.translatorUuid != null }
                .sortedBy { it.translatorPriority }.map { it.translatorUuid!! }.distinct()
        )
    }.first()
