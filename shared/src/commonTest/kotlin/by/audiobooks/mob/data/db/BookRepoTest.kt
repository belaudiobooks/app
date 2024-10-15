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
import kotlin.test.assertNotNull

class BookRepoTest {

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
    fun getAllBooksTest() = runTest {
        val state = dbHelper.database.getAllBooks(UnconfinedTestDispatcher()).stateIn(backgroundScope)
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
    fun getNLatestNarrationAsBookCoversTest() = runTest {
        val state = dbHelper.database.getNLatestNarrationAsBookCovers(4, UnconfinedTestDispatcher())
            .stateIn(backgroundScope)
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
    fun getBookByUuidTest() = runTest {
        // Insert test data
        dbHelper.database.insertBook(DBTestData.extraBook)

        val state = dbHelper.database.getBookByUuid("14196350-090b-4d63-a964-920773ba1df2", UnconfinedTestDispatcher())
            .stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Verify DB record has correct book Uuid:
        assertEquals("14196350-090b-4d63-a964-920773ba1df2", state.value.bookUuid)

        // Verify DB record has correct book title:
        assertEquals("Яшчэ Адна Кніга", state.value.bookTitle)

        // Replace initial entry:
        dbHelper.database.transaction {
            dbHelper.database.cleanUpDB()
            dbHelper.database.insertBook(DBTestData.extraBook.copy(title = "Яшчэ Адна Кніга2"))
        }
        runCurrent()

        // Verify state get update
        assertEquals("Яшчэ Адна Кніга2", state.value.bookTitle)
    }

    @Test
    fun getBookDetailsTest() = runTest {
        // Insert test data:
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)

        val state = dbHelper.getBookDetails("977e535e-1e2a-4c95-bf3b-629ff80aee94")
            .stateIn(backgroundScope)
        backgroundScope.launch { state.collect() }
        runCurrent()

        assertNotNull(state)
        assertEquals("977e535e-1e2a-4c95-bf3b-629ff80aee94", state.value.uuid)
        assertEquals("Першая Кніга", state.value.title)
        assertEquals(1, state.value.narrations.size)
        assertEquals(2, state.value.tags.size)
    }
}