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

class TagFunctionsTest {

    private var dbHelper = getTestDatabaseHelper()

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

        // It is important to inject UnconfinedTestDispatcher() (implementation of TestDispatcher)
        // to the query execution logic to let it schedule DB internal tasks for the proper functioning
        // flow of result sets (updating StateFlow).
        // See https://developer.android.com/kotlin/coroutines/test#invoking-suspending-functions
        val state = dbHelper.database.getAllTags(UnconfinedTestDispatcher()).stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Verify DB is empty
        assertEquals(0, state.value.size)

        // Insert data snapshot & verify result got updated.
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent() // yield op to let StateFlow receive new result set.
        assertEquals(3, state.value.size)

        // Insert additional data & verify result got updated.
        dbHelper.database.insertTag(DBTestData.extraTag)
        runCurrent() // yield op to let StateFlow receive new result set.
        assertEquals(4, state.value.size)
    }

    @Test
    fun getTagsByBookUuidSubscriptionTest() = runTest {
        // It is important to inject UnconfinedTestDispatcher() (implementation of TestDispatcher)
        // to the query execution logic to let it schedule DB internal tasks for the proper functioning
        // flow of result sets (updating StateFlow).
        // See https://developer.android.com/kotlin/coroutines/test#invoking-suspending-functions
        val state = dbHelper.database.getTagsByBookUuidSubscription("977e535e-1e2a-4c95-bf3b-629ff80aee94", UnconfinedTestDispatcher()).stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Verify DB is empty
        assertEquals(0, state.value.size)

        // Insert data snapshot & verify result got updated.
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent() // yield op to let StateFlow receive new result set.
        // check that list contains exactly two tags
        assertEquals(2, state.value.size)
        assertTrue {
            // check these tags are 0 and 2
            state.value.filter { it.id == 0L || it.id ==2L }.size == 2
        }
    }

    @Test
    fun getTagsByBookUuidTest() = runTest {
        // Insert test data
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent()

        // Get data from DB
        val tags = dbHelper.database.getTagsByBookUuid("977e535e-1e2a-4c95-bf3b-629ff80aee94")

        // check that list contains exactly two tags
        assertEquals(2, tags.size)
        assertTrue {
            // check these tags are 0 and 2
            tags.filter { it.id == 0L || it.id ==2L }.size == 2
        }
    }

    @Test
    fun getTagByIdTest() = runTest {
        // Insert test data
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent()

        // Subscribe
        val tagState = dbHelper.database.getTagById(2L, UnconfinedTestDispatcher()).stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { tagState.collect() }

        // check that list contains exactly two tags
        assertEquals(2L, tagState.value.id)
        assertEquals("Дзецям і падлеткам", tagState.value.name)
        assertEquals(2L, tagState.value.bookCount)
    }
}