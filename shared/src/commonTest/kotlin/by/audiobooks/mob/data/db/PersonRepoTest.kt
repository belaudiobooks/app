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

class PersonRepoTest {

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
    fun getAllPersonsTest() = runTest {
        val state = dbHelper.database.getAllPersons(UnconfinedTestDispatcher()).stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Verify DB is empty
        assertEquals(0, state.value.size)

        // Insert data snapshot & verify result got updated.
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent()
        assertEquals(3, state.value.size)

        // Insert additional data & verify result got updated.
        dbHelper.database.insertPerson(DBTestData.extraPerson)
        runCurrent()
        assertEquals(4, state.value.size)
    }

    @Test
    fun getPersonByUuidTest() = runTest {
        // Prepare initial data:
        dbHelper.database.insertPerson(DBTestData.extraPerson)

        // Subscribe to updates:
        val state = dbHelper.database.getPersonByUuid(DBTestData.extraPerson.uuid, UnconfinedTestDispatcher()).stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Make sure subscription returned expected initial data
        assertEquals(DBTestData.extraPerson.name, state.value.name)

        // Replace initial entry:
        dbHelper.database.transaction {
            dbHelper.database.cleanUpDB()
            dbHelper.database.insertPerson(DBTestData.extraPerson.copy(name = "Updated-Name"))
        }

        runCurrent()
        // Check that subscription returned updated entry
        assertEquals("Updated-Name", state.value.name)
    }

    @Test
    fun getAuthorsByBookUuidTest() = runTest {
        val state = dbHelper.database.getAuthorsByBookUuid("977e535e-1e2a-4c95-bf3b-629ff80aee94", UnconfinedTestDispatcher()).stateIn(backgroundScope)
        backgroundScope.launch(UnconfinedTestDispatcher()) { state.collect() }

        // Verify DB is empty
        assertEquals(0, state.value.size)

        // Insert data snapshot & verify result got updated.
        dbHelper.database.replaceData(DBTestData.testDataSnapshot)
        runCurrent()
        assertEquals(1, state.value.size)
        assertEquals("e50f35f5-c134-4c82-90cc-8391fd676f3d", state.value.first().uuid)
    }
}