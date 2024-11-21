package by.audiobooks.mob.data.db

import app.cash.sqldelight.db.SqlDriver
import by.audiobooks.mob.data.network.dto.BackendDataSnapshot
import by.audiobooks.mob.domain.Book
import by.audiobooks.mob.domain.BookCover
import by.audiobooks.mob.domain.BookDetails
import by.audiobooks.mob.domain.Link
import by.audiobooks.mob.domain.LinkType
import by.audiobooks.mob.domain.Narration
import by.audiobooks.mob.domain.Person
import by.audiobooks.mob.domain.Publisher
import by.audiobooks.mob.domain.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DatabaseHelper(sqlDriver: SqlDriver) {

    internal val database by lazy { AudiobooksByDB(sqlDriver) }

    suspend fun replaceData(newData: BackendDataSnapshot): Unit = withContext(Dispatchers.IO) {
        database.replaceData(newData)
    }

    fun getAllBooks(): Flow<List<Book>> = database.getAllBooks()

    fun getNLatestNarrationsAsBookCovers(numberOfBookCovers: Long): Flow<List<BookCover>> =
        database.getNLatestNarrationAsBookCovers(numberOfBookCovers)

    fun getBookDetails(bookUuid: String): Flow<BookDetails> =
        combine(
            database.getBookByUuid(bookUuid),
            database.getNarrationsWithDetailsByBookUuidSubscription(bookUuid),
            database.getAuthorsByBookUuidSubscription(bookUuid),
            database.getTagsByBookUuidSubscription(bookUuid)
        ) { bookInfo, narrations, authors, tags ->
            BookDetails(
                uuid = bookInfo.bookUuid,
                title = bookInfo.bookTitle,
                description = bookInfo.bookDescription,
                descriptionSource = bookInfo.bookDescriptionSource,
                narrations = narrations,
                authors = authors,
                tags = tags
            )
        }

    fun getBooksDetailsByTagId(tagId: Long): Flow<List<BookDetails>> =
        database.getBooksByTagId(tagId).map {
            it.map { book ->
                BookDetails(
                    uuid = book.bookUuid,
                    title = book.bookTitle,
                    description = book.bookDescription,
                    descriptionSource = book.bookDescriptionSource,
                    narrations = database.getNarrationsWithDetailsByBookUuid(book.bookUuid),
                    authors = database.getAuthorsByBookUuid(book.bookUuid),
                    tags = database.getTagsByBookUuid(book.bookUuid)
                )
            }
        }

    fun getBooksDetailsByPublisherUuid(publisherUuid: String): Flow<List<BookDetails>> =
        database.getBooksByPublisherUuid(publisherUuid).map {
            it.map { book ->
                BookDetails(
                    uuid = book.bookUuid,
                    title = book.bookTitle,
                    description = book.bookDescription,
                    descriptionSource = book.bookDescriptionSource,
                    narrations = database.getNarrationsWithDetailsByBookUuid(book.bookUuid),
                    authors = database.getAuthorsByBookUuid(book.bookUuid),
                    tags = database.getTagsByBookUuid(book.bookUuid)
                )
            }
        }

    fun getBooksDetailsByAuthorUuid(personUuid: String): Flow<List<BookDetails>> =
        database.getBooksByAuthorUuid(personUuid).map {
            it.map { book ->
                BookDetails(
                    uuid = book.bookUuid,
                    title = book.bookTitle,
                    description = book.bookDescription,
                    descriptionSource = book.bookDescriptionSource,
                    narrations = database.getNarrationsWithDetailsByBookUuid(book.bookUuid),
                    authors = database.getAuthorsByBookUuid(book.bookUuid),
                    tags = database.getTagsByBookUuid(book.bookUuid)
                )
            }
        }

    fun getBooksDetailsByTranslatorUuid(personUuid: String): Flow<List<BookDetails>> =
        database.getBooksByTranslatorUuid(personUuid).map {
            it.map { book ->
                BookDetails(
                    uuid = book.bookUuid,
                    title = book.bookTitle,
                    description = book.bookDescription,
                    descriptionSource = book.bookDescriptionSource,
                    narrations = database.getNarrationsWithDetailsByBookUuid(book.bookUuid),
                    authors = database.getAuthorsByBookUuid(book.bookUuid),
                    tags = database.getTagsByBookUuid(book.bookUuid)
                )
            }
        }

    fun getBooksDetailsByNarratorUuid(personUuid: String): Flow<List<BookDetails>> =
        database.getBooksByNarratorUuid(personUuid).map {
            it.map { book ->
                BookDetails(
                    uuid = book.bookUuid,
                    title = book.bookTitle,
                    description = book.bookDescription,
                    descriptionSource = book.bookDescriptionSource,
                    narrations = database.getNarrationsWithDetailsByBookUuid(book.bookUuid),
                    authors = database.getAuthorsByBookUuid(book.bookUuid),
                    tags = database.getTagsByBookUuid(book.bookUuid)
                )
            }
        }

    fun getAllNarrations(): Flow<List<Narration>> = database.getAllNarrations()

    fun getAllPersons(): Flow<List<Person>> = database.getAllPersons()

    fun getPersonByUuid(personUuid: String): Flow<Person> = database.getPersonByUuidSubscription(personUuid)

    fun getAllPublishers(): Flow<List<Publisher>> = database.getAllPublishers()

    fun getPublisherByUuid(publisherUuid: String): Flow<Publisher> = database.getPublisherByUuidSubscription(publisherUuid)

    fun getAllTags(): Flow<List<Tag>> = database.getAllTags()

    fun getAllLinks(): Flow<List<Link>> = database.getAllLinks()

    fun getAllLinkTypes(): Flow<List<LinkType>> = database.getAllLinkTypes()

    fun getTagById(tagId: Long): Flow<Tag> = database.getTagById(tagId)

}
