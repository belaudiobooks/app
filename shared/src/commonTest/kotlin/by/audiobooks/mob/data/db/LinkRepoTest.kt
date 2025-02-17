package by.audiobooks.mob.data.db

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LinkRepoTest {

    private val dbHelper = getTestDatabaseHelper()

    @BeforeTest
    fun setUp() {
        dbHelper.database.cleanUpDB()
    }

    @AfterTest
    fun tearDown() {
        dbHelper.database.cleanUpDB()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun linkRepoTest() = runTest {
        val state = dbHelper.database.getAllLinks(UnconfinedTestDispatcher()).stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Verify DB is empty
        assertEquals(0, state.value.size)

        // Insert data snapshot & verify result got updated.
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent()
        assertEquals(7, state.value.size)

        // Insert additional data & verify result got updated.
        dbHelper.database.insertBook(DBTestData.extraBook)
        runCurrent()
        assertEquals(10, state.value.size)
    }

    @Test
    fun getLinkDetailsByNarrationUuidTest() {
        // Insertion entire test data snapshot since LinkType records are required for query
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        // Test based on extraBook record
        dbHelper.database.insertBook(DBTestData.extraBook)

        val listOfLinkDetails = dbHelper.database.getLinksDetailsByNarrationUuid(DBTestData.extraBook.narrations.first().uuid)

        assertEquals(3, listOfLinkDetails.size)
    }
}
