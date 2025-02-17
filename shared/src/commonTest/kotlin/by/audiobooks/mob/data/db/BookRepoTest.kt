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
import kotlin.test.assertContentEquals
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

    @OptIn(ExperimentalCoroutinesApi::class)
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

    @OptIn(ExperimentalCoroutinesApi::class)
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

    @OptIn(ExperimentalCoroutinesApi::class)
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getBooksByTagIdTest() = runTest {
        // Subscribe
        val state = dbHelper.database.getBooksByTagId(2L, UnconfinedTestDispatcher())
            .stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Make sure database is empty
        assertEquals(0, state.value.size)

        // Insert test data
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent()

        // Make sure we have two books in the result set
        assertEquals(2, state.value.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getBooksDetailsByTagIdTest() = runTest {
        // Insert test data:
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)

        // Subscribe
        val booksDetails = dbHelper.getBooksDetailsByTagId(2L)
            .stateIn(backgroundScope)
        backgroundScope.launch { booksDetails.collect() }
        runCurrent()

        // Verify
        assertNotNull(booksDetails)
        assertEquals(booksDetails.value.size, 2)
        assertEquals(booksDetails.value.map { it.uuid }
            .containsAll(
                listOf(
                    "5cf6f8bd-796c-43f8-9b32-8cd3dd58ab70",
                    "977e535e-1e2a-4c95-bf3b-629ff80aee94"
                )
            ),
            true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getBooksDetailsByPublisherUuidTest() = runTest {
        // Insert test data:
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)

        // Subscribe
        val booksDetails = dbHelper.getBooksDetailsByPublisherUuid("e82f5ab3-6c85-4d0a-8389-91a4ea484dae")
            .stateIn(backgroundScope)
        backgroundScope.launch { booksDetails.collect() }
        runCurrent()

        // Verify
        assertNotNull(booksDetails)
        assertEquals(booksDetails.value.size, 1)
        assertEquals(booksDetails.value.map { it.uuid }
            .containsAll(
                listOf(
                    "977e535e-1e2a-4c95-bf3b-629ff80aee94"
                )
            ),
            true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getBooksDetailsByAuthorUuidTest() = runTest {
        // Insert test data:
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)

        // Subscribe
        val booksDetails = dbHelper.getBooksDetailsByAuthorUuid("e50f35f5-c134-4c82-90cc-8391fd676f3d")
            .stateIn(backgroundScope)
        backgroundScope.launch { booksDetails.collect() }
        runCurrent()

        // Verify
        assertNotNull(booksDetails)
        assertEquals(booksDetails.value.size, 1)
        assertEquals(booksDetails.value.map { it.uuid }
            .containsAll(
                listOf(
                    "977e535e-1e2a-4c95-bf3b-629ff80aee94"
                )
            ),
            true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getBooksDetailsByTranslatorUuidTest() = runTest {
        // Insert test data:
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)

        // Subscribe
        val booksDetails = dbHelper.getBooksDetailsByTranslatorUuid("a240893c-a042-45fb-bcca-edee57961917")
            .stateIn(backgroundScope)
        backgroundScope.launch { booksDetails.collect() }
        runCurrent()

        // Verify
        assertNotNull(booksDetails)
        assertEquals(booksDetails.value.size, 2)
        assertEquals(booksDetails.value.map { it.uuid }
            .containsAll(
                listOf(
                    "98c04aca-9eae-4e45-aaf6-39f69aaf9da1",
                    "5cf6f8bd-796c-43f8-9b32-8cd3dd58ab70"
                )
            ),
            true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getBooksDetailsByNarratorUuidTest() = runTest {
        // Insert test data:
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)

        // Subscribe
        val booksDetails = dbHelper.getBooksDetailsByNarratorUuid("a240893c-a042-45fb-bcca-edee57961917")
            .stateIn(backgroundScope)
        backgroundScope.launch { booksDetails.collect() }
        runCurrent()

        // Verify
        assertNotNull(booksDetails)
        assertEquals(3, booksDetails.value.size)
        assertEquals(booksDetails.value.map { it.uuid }
            .containsAll(
                listOf(
                    "977e535e-1e2a-4c95-bf3b-629ff80aee94",
                    "98c04aca-9eae-4e45-aaf6-39f69aaf9da1",
                    "5cf6f8bd-796c-43f8-9b32-8cd3dd58ab70"
                )
            ),
            true)
    }
}