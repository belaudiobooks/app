package by.audiobooks.mob.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import by.audiobooks.mob.domain.Book
import by.audiobooks.mob.domain.BookCover
import by.audiobooks.mob.domain.Gender
import by.audiobooks.mob.domain.Person
import by.audiobooks.mob.domain.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate
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

internal fun AudiobooksByDB.getNLatestNarrationAsBookCovers(numberOfBookCovers: Long, context: CoroutineContext = Dispatchers.IO): Flow<List<BookCover>> =
    bookQueries.selectNLatestNarrationsAsBookCovers(numberOfBookCovers).asFlow().mapToList(context)
        .map { it.groupBy { record -> record.bookUuid } }
        .map { map -> map.values
            .map { recordGroup ->  recordGroup.map { record ->
                BookCover(
                    uuid = record.bookUuid,
                    title = record.bookTitle,
                    coverImage = record.narrationCoverImage,
                    date = LocalDate.parse(record.narrationPublicationDate),
                    description = record.bookDescription,
                    descriptionSource = record.bookDescriptionSource,
                    authors = recordGroup.sortedBy { it.authorPriority }.map { record ->
                        Person(
                            uuid = record.authorUuid,
                            name = record.authorName,
                            description = record.authorDescription,
                            descriptionSource = record.authorDescriptionSource,
                            photo = record.authorPhoto,
                            photoSource = record.authorPhotoSource,
                            gender = Gender.valueOf(record.authorGender)
                        )
                    }.distinct(),
                    tags = recordGroup.map { item ->
                        Tag(
                            id = item.tagId,
                            name = item.tagName,
                            description = item.tagDescription,
                            bookCount = item.tagBookCount
                        )
                    }.distinct()
                )}.first()
            }.sortedBy { it.date }.reversed()
        }

internal fun AudiobooksByDB.getBookByUuid(bookUuid: String, context: CoroutineContext = Dispatchers.IO): Flow<SelectBookByUuid> =
    bookQueries.selectBookByUuid(bookUuid).asFlow().mapToOne(context)

internal fun AudiobooksByDB.getBooksByTagId(tagId: Long, context: CoroutineContext = Dispatchers.IO): Flow<List<SelectBooksByTagId>> =
    bookQueries.selectBooksByTagId(tagId).asFlow().mapToList(context)
