package by.audiobooks.mob.data

import by.audiobooks.mob.data.db.DatabaseHelper
import by.audiobooks.mob.data.network.AlgoliaSearchApi
import by.audiobooks.mob.data.network.SiteApi
import by.audiobooks.mob.data.network.dto.SearchResultModel
import by.audiobooks.mob.domain.Book
import by.audiobooks.mob.domain.BookCover
import by.audiobooks.mob.domain.BookDetails
import by.audiobooks.mob.domain.Link
import by.audiobooks.mob.domain.LinkType
import by.audiobooks.mob.domain.Narration
import by.audiobooks.mob.domain.Person
import by.audiobooks.mob.domain.Publisher
import by.audiobooks.mob.domain.SearchResults
import by.audiobooks.mob.domain.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext

class RepositoryImpl(
    val dbHelper: DatabaseHelper,
    val siteApi: SiteApi,
    val algoliaSearchApi: AlgoliaSearchApi
): Repository {

    override suspend fun refreshData(): Unit = withContext(Dispatchers.IO) {
        dbHelper.replaceData(siteApi.downloadData())
    }

    override suspend fun search(query: String): Flow<SearchResults> {
        val algoliaResults = algoliaSearchApi.search(query)
        val books = algoliaResults.filter { it.model == SearchResultModel.BOOK }.map { dbHelper.getBookDetails(it.objectId) }
        val publishers = algoliaResults.filter { it.model == SearchResultModel.PUBLISHER }.map { dbHelper.getPublisherByUuid(it.objectId) }
        val persons = algoliaResults.filter { it.model == SearchResultModel.PERSON }.map { dbHelper.getPersonByUuid(it.objectId) }
        return combine(
            combine(books) {flows -> flows.toList() },
            combine(publishers) {flows -> flows.toList() },
            combine(persons) {flows -> flows.toList() }
        ) { bookHits, publisherHits, personHits ->
            SearchResults(bookHits, publisherHits, personHits) }
    }

    override fun getAllBooks(): Flow<List<Book>> = dbHelper.getAllBooks()

    override fun getNLatestNarrationsAsBookCovers(numberOfBookCovers: Long): Flow<List<BookCover>> =
        dbHelper.getNLatestNarrationsAsBookCovers(numberOfBookCovers)

    override fun getBookDetails(bookUuid: String): Flow<BookDetails>  = dbHelper.getBookDetails(bookUuid)

    override fun getBooksDetailsByTagId(id: Long): Flow<List<BookDetails>> = dbHelper.getBooksDetailsByTagId(id)

    override fun getAllNarrations(): Flow<List<Narration>> = dbHelper.getAllNarrations()

    override fun getAllPersons(): Flow<List<Person>> = dbHelper.getAllPersons()

    override fun getAllPublishers(): Flow<List<Publisher>> = dbHelper.getAllPublishers()

    override fun getAllTags(): Flow<List<Tag>> = dbHelper.getAllTags()

    override fun getTagById(tagId: Long): Flow<Tag> = dbHelper.getTagById(tagId)

    override fun getAllLinks(): Flow<List<Link>> = dbHelper.getAllLinks()

    override fun getAllLinkTypes(): Flow<List<LinkType>> = dbHelper.getAllLinkTypes()
}
