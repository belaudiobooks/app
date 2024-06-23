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
    fun tagRepoTest() = runTest {

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
}