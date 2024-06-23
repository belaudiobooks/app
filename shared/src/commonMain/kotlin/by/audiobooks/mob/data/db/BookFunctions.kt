package by.audiobooks.mob.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import by.audiobooks.mob.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

internal fun AudiobooksByDB.getAllBooks(context: CoroutineContext = Dispatchers.IO): Flow<List<Book>> =
    bookQueries.selectAllBooks().asFlow().mapToList(context)
        .map { it.groupBy { record -> record.bookUuid } }
        .map { map -> map.values
            .map { recordGroup ->  recordGroup.map { record ->
                Book(
                    uuid = record.bookUuid,
                    title = record.bookTitle,
                    description = record.bookDescription,
                    descriptionSource = record.bookDescriptionSource,
                    authorUuids = recordGroup
                        .filter { it.authorPriority != null && it.authorUuid != null }
                        .sortedBy { it.authorPriority }.map { it.authorUuid!! }.distinct(),
                    tagIds = recordGroup
                        .filter { it.bookTag != null }.map { it.bookTag!! }.distinct()
                )
            }.first()
            }}
