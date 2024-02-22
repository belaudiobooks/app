package by.audiobooks.mob.cache

import by.audiobooks.mob.model.source.Gender
import by.audiobooks.mob.model.source.Person
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PersonRepoTest {

    private val repository = getTestRepository()

    @Test
    fun linkTypeRepoTest() = runTest {
        assertTrue(repository.database.getAllPersons().isEmpty())

        val person1 = Person(
            uuid = "dsdf-sdfsd-fsdf-person-1", name = "Volha Volhauna",
            description = "person-1", descriptionSource = "",
            photo = "http://adsdasdas.com/person-1.png",
            photoSource = "", slug = "volha-volhauna", gender = Gender.FEMALE)
        val person2 = Person(
            uuid = "dsdf-sdfsd-fsdf-person-2", name = "Siarhei Siarheey",
            description = "person-2", descriptionSource = "",
            photo = "http://adsdasdas.com/person-2.png",
            photoSource = "", slug = "siarhei-siarheey", gender = Gender.MALE)
        val person3 = Person(
            uuid = "dsdf-sdfsd-fsdf-person-3", name = "Hruppa ludziej",
            description = "person-3", descriptionSource = "",
            photo = "http://adsdasdas.com/person-3.png",
            photoSource = "", slug = "hruppa-ludziej", gender = Gender.PLURAL)

        repository.database.insertPerson(person1)
        repository.database.insertPerson(person2)
        repository.database.insertPerson(person3)

        val personsFromDb = repository.database.getAllPersons()
        assertTrue( personsFromDb.size == 3)
        assertContentEquals(personsFromDb, listOf(person1, person2, person3))

        val actualPersons2 = repository.database.getPersonByUuid("dsdf-sdfsd-fsdf-person-2")
        assertEquals(actualPersons2, person2)

        repository.database.deleteAllPersons()
        assertTrue(repository.database.getAllPersons().isEmpty())
    }
}