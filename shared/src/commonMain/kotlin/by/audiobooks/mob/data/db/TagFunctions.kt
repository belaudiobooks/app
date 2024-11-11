package by.audiobooks.mob.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import by.audiobooks.mob.domain.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

internal fun AudiobooksByDB.getAllTags(context: CoroutineContext = Dispatchers.IO): Flow<List<Tag>> =
    tagQueries.selectAllTags { tagId, tagName, tagDescription, bookCount ->
        Tag(
            id = tagId,
            name = tagName,
            description = tagDescription,
            bookCount = bookCount
    )}.asFlow().mapToList(context)

internal fun AudiobooksByDB.getTagById(tagId: Long, context: CoroutineContext = Dispatchers.IO): Flow<Tag> =
    tagQueries.selectTagById(tagId).asFlow().mapToOne(context)
        .map { Tag(it.tagId, it.tagName, it.tagDescription, it.bookCount) }

internal fun AudiobooksByDB.getTagsByBookUuidSubscription(bookUuid: String, context: CoroutineContext = Dispatchers.IO): Flow<List<Tag>> =
    tagQueries.selectTagsByBookUuid(bookUuid).asFlow().mapToList(context)
        .map { it.map { tag -> tagMapper(tag) } }

internal fun AudiobooksByDB.getTagsByBookUuid(bookUuid: String): List<Tag> =
    tagQueries.selectTagsByBookUuid(bookUuid).executeAsList()
        .map { tag -> tagMapper(tag) }

private fun tagMapper(record: SelectTagsByBookUuid): Tag =
    Tag(
        id = record.tagId,
        name = record.tagName,
        description = record.tagDescription,
        bookCount = record.bookCount
    )
