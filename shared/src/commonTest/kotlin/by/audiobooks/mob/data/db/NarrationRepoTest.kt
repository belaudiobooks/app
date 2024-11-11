package by.audiobooks.mob.data.db

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
import kotlin.test.assertTrue

class NarrationRepoTest {

    private val dbHelper = getTestDatabaseHelper()

    @BeforeTest
    fun setUp() {
        dbHelper.database.cleanUpDB()
    }

    @AfterTest
    fun tearDown() {
        dbHelper.database.cleanUpDB()
    }

    @Test
    fun narrationRepoTest() = runTest {
        val state = dbHelper.database.getAllNarrations(UnconfinedTestDispatcher()).stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Verify DB is empty
        assertEquals(0, state.value.size)

        // Insert data snapshot & verify result got updated.
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent()
        assertEquals(3, state.value.size)

        // Insert additional data & verify result got updated.
        dbHelper.database.insertBook(DBTestData.extraBook)
        runCurrent()
        assertEquals(4, state.value.size)
    }

    @Test
    fun getNarrationsWithDetailsByBookUuidSubscriptionTest() = runTest {
        val unconfinedTestDispatcher = UnconfinedTestDispatcher()
        val state = dbHelper.database.getNarrationsWithDetailsByBookUuidSubscription("977e535e-1e2a-4c95-bf3b-629ff80aee94",
            unconfinedTestDispatcher).stateIn(backgroundScope)
        backgroundScope.launch(unconfinedTestDispatcher) { state.collect() }

        // Verify DB is empty
        assertEquals(0, state.value.size)

        // Insert data snapshot & verify result got updated.
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent()
        assertEquals(1, state.value.size)
        assertTrue {
            val testNarration = state.value.first()
            testNarration.uuid == "5942b109-714a-477c-8266-d776c7f2fbd7" &&
                    testNarration.narrators.isNotEmpty() &&
                    testNarration.publishers.isNotEmpty() &&
                    testNarration.translators.isEmpty()
        }
    }

    @Test
    fun getNarrationsWithDetailsByBookUuidTest() = runTest {
        // Insert data snapshot & verify result got updated.
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent()

        // Get data from database
        val narrationsWithDetails = dbHelper.database.getNarrationsWithDetailsByBookUuid("977e535e-1e2a-4c95-bf3b-629ff80aee94")

        // Verify
        assertEquals(1, narrationsWithDetails.size)
        assertTrue {
            val testNarration = narrationsWithDetails.first()
            testNarration.uuid == "5942b109-714a-477c-8266-d776c7f2fbd7" &&
                    testNarration.narrators.isNotEmpty() &&
                    testNarration.publishers.isNotEmpty() &&
                    testNarration.translators.isEmpty()
        }
    }
}