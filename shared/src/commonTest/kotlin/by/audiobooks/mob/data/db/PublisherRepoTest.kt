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

class PublisherRepoTest {

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
    fun publisherRepoTest() = runTest {
        val state = dbHelper.database.getAllPublishers(UnconfinedTestDispatcher()).stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Verify DB is empty
        assertEquals(0, state.value.size)

        // Insert data snapshot & verify result got updated.
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent()
        assertEquals(5, state.value.size)

        // Insert additional data & verify result got updated.
        dbHelper.database.insertPublisher(DBTestData.extraPublisher)
        runCurrent()
        assertEquals(6, state.value.size)
    }

    @Test
    fun getPublishersByUuidTest() {
        // Insert test data:
        dbHelper.database.insertPublisher(DBTestData.extraPublisher)

        // Query record from db:
        val testPublisher = dbHelper.database.getPublisherByUuid(DBTestData.extraPublisher.uuid)

        // Make sure subscription returned expected initial data
        assertEquals(DBTestData.extraPublisher.name, testPublisher?.name)

        // Replace initial entry:
        dbHelper.database.transaction {
            dbHelper.database.cleanUpDB()
            dbHelper.database.insertPublisher(DBTestData.extraPublisher.copy(name = "Updated-Name"))
        }

        val testUpdatedPublisher = dbHelper.database.getPublisherByUuid(DBTestData.extraPublisher.uuid)

        // Check that subscription returned updated entry
        assertEquals("Updated-Name", testUpdatedPublisher?.name)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPublishersByUuidSubscriptionTest() = runTest {
        // Insert test data:
        dbHelper.database.insertPublisher(DBTestData.extraPublisher)

        // Query record from db:
        val state = dbHelper.database.getPublisherByUuidSubscription(DBTestData.extraPublisher.uuid, UnconfinedTestDispatcher()).stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Make sure subscription returned expected initial data
        assertEquals(DBTestData.extraPublisher.name, state.value.name)

        // Replace initial entry:
        dbHelper.database.transaction {
            dbHelper.database.cleanUpDB()
            dbHelper.database.insertPublisher(DBTestData.extraPublisher.copy(name = "Updated-Name"))
        }
        runCurrent()

        // Check that subscription returned updated entry
        assertEquals("Updated-Name", state.value.name)
    }
}