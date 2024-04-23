package by.audiobooks.mob.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import by.audiobooks.mob.domain.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

internal fun AudiobooksByDB.getAllTags(context: CoroutineContext = Dispatchers.IO): Flow<List<Tag>> =
    tagQueries.selectAllTags { tagId, tagName, tagDescription, bookCount ->
        Tag(
            id = tagId,
            name = tagName,
            description = tagDescription,
            bookCount = bookCount
    )}.asFlow().mapToList(context)
