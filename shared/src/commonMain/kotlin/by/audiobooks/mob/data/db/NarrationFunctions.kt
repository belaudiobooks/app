package by.audiobooks.mob.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import by.audiobooks.mob.domain.Language
import by.audiobooks.mob.domain.Narration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate
import kotlin.coroutines.CoroutineContext

internal fun AudiobooksByDB.getAllNarrations(context: CoroutineContext = Dispatchers.IO): Flow<List<Narration>> =
    narrationQueries.selectAllNarrations().asFlow().mapToList(context)
        .map { it.groupBy { record -> record.narrationUuid } }
        .map { map -> map.values
            .map { recordGroup ->
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
            }}
