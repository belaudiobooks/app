package by.audiobooks.mob.data

import by.audiobooks.mob.data.db.DatabaseHelper
import by.audiobooks.mob.data.network.SiteApi
import by.audiobooks.mob.domain.Book
import by.audiobooks.mob.domain.BookCover
import by.audiobooks.mob.domain.Link
import by.audiobooks.mob.domain.LinkType
import by.audiobooks.mob.domain.Narration
import by.audiobooks.mob.domain.Person
import by.audiobooks.mob.domain.Publisher
import by.audiobooks.mob.domain.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RepositoryImpl(
    val dbHelper: DatabaseHelper,
    val siteApi: SiteApi
): Repository {

    override suspend fun refreshData(): Unit = withContext(Dispatchers.IO) {
        dbHelper.replaceData(siteApi.downloadData())
    }

    override fun getAllBooks(): Flow<List<Book>> = dbHelper.getAllBooks()

    override fun getNLatestNarrationsAsBookCovers(numberOfBookCovers: Long): Flow<List<BookCover>> =
        dbHelper.getNLatestNarrationsAsBookCovers(numberOfBookCovers)

    override fun getAllNarrations(): Flow<List<Narration>> = dbHelper.getAllNarrations()

    override fun getAllPersons(): Flow<List<Person>> = dbHelper.getAllPersons()

    override fun getAllPublishers(): Flow<List<Publisher>> = dbHelper.getAllPublishers()

    override fun getAllTags(): Flow<List<Tag>> = dbHelper.getAllTags()

    override fun getAllLinks(): Flow<List<Link>> = dbHelper.getAllLinks()

    override fun getAllLinkTypes(): Flow<List<LinkType>> = dbHelper.getAllLinkTypes()
}