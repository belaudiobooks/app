package by.audiobooks.mob.data

import by.audiobooks.mob.domain.Book
import by.audiobooks.mob.domain.BookCover
import by.audiobooks.mob.domain.BookDetails
import by.audiobooks.mob.domain.Link
import by.audiobooks.mob.domain.LinkType
import by.audiobooks.mob.domain.Narration
import by.audiobooks.mob.domain.Person
import by.audiobooks.mob.domain.PersonsBooksDetails
import by.audiobooks.mob.domain.Publisher
import by.audiobooks.mob.domain.SearchResults
import by.audiobooks.mob.domain.Tag
import kotlinx.coroutines.flow.Flow

interface Repository {

    /**
     * Asynchronously update db from remote server.
     */
    suspend fun refreshData()

    /**
     * Get subscription to results of algolia search.
     */
    suspend fun search(query: String): Flow<SearchResults>

    /**
     * Flow of N most recently added narrations represented by [BookCover] records.
     */
    fun getNLatestNarrationsAsBookCovers(numberOfBookCovers: Long = 10): Flow<List<BookCover>>

    /**
     * Get subscription to actual state of [BookDetails] by uuid of book.
     */
    fun getBookDetails(bookUuid: String): Flow<BookDetails>

    /**
     * Flow of all [Tag] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllTags(): Flow<List<Tag>>

    /**
     * Subscription to single [Tag] updates.
     */
    fun getTagById(tagId: Long): Flow<Tag>

    /**
     * Get subscription to list of [BookDetails] by tag id.
     */
    fun getBooksDetailsByTagId(id: Long): Flow<List<BookDetails>>

    /**
     * Get subscription to list of [BookDetails] by publisher uuid.
     */
    fun getBooksDetailsByPublisherUuid(publisherUuid: String): Flow<List<BookDetails>>

    /**
     * Get subscription to collection of [BooksDetails] by person uuid.
     */
    fun getBooksDetailsByPersonUuid(personUuid: String): Flow<PersonsBooksDetails>

    /**
     * Flow of all [Book] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllBooks(): Flow<List<Book>>

    /**
     * Flow of all [Narration] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllNarrations(): Flow<List<Narration>>

    /**
     * Flow of all [Person] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllPersons(): Flow<List<Person>>

    /**
     * Flow of all [Publisher] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllPublishers(): Flow<List<Publisher>>

    /**
     * Flow of all [Link] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllLinks(): Flow<List<Link>>

    /**
     * Flow of all [LinkType] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllLinkTypes(): Flow<List<LinkType>>
}
