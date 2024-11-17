package by.audiobooks.mob.data

import by.audiobooks.mob.data.db.cleanUpDB
import by.audiobooks.mob.data.db.getTestDatabaseHelper
import by.audiobooks.mob.data.network.AlgoliaSearchApi
import by.audiobooks.mob.data.network.SiteApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RepositorySearchTest {

    private var dbHelper = getTestDatabaseHelper()
    private var repo = RepositoryImpl(
        siteApi = SiteApi(),
        dbHelper = dbHelper,
        algoliaSearchApi = AlgoliaSearchApi()
    )

    @BeforeTest
    fun setUp() {
        dbHelper.database.cleanUpDB()
    }

    @AfterTest
    fun tearDown() {
        dbHelper.database.cleanUpDB()
    }

    @Test
    fun getAllTagsTest() = runTest {
        repo.refreshData()
        val hits = repo.search("яну").first()
        assertNotNull(hits)
        assertTrue { hits.publisherHits.isNotEmpty() }
        assertTrue { hits.bookHits.isNotEmpty() }
        assertTrue { hits.personHits.isNotEmpty() }
    }

}