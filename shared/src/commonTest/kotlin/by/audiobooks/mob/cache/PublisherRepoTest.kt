package by.audiobooks.mob.cache

import by.audiobooks.mob.model.source.Publisher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PublisherRepoTest {

    private val repository = getTestRepository()

    @Test
    fun linkTypeRepoTest() = runTest {
        assertTrue(repository.database.getAllPublishers().isEmpty())

        val publisher1 = Publisher(
            uuid = "dsdf-sdfsd-fsdf-publisher-1",
            name = "MegaPublisher1",
            slug = "mega-publisher-1",
            url = "http://publisher-1.com/",
            logo = "http://adsdasdas.com/publisher-1.png",
            description = "publisher-1")
        val publisher2 = Publisher(
            uuid = "dsdf-sdfsd-fsdf-publisher-2",
            name = "MegaPublisher2",
            slug = "mega-publisher-2",
            url = "http://publisher-2.com/",
            logo = "http://adsdasdas.com/publisher-2.png",
            description = "publisher-2")
        val publisher3 = Publisher(
            uuid = "dsdf-sdfsd-fsdf-publisher-3",
            name = "MegaPublisher3",
            slug = "mega-publisher-3",
            url = "http://publisher-3.com/",
            logo = "http://adsdasdas.com/publisher-3.png",
            description = "publisher-3")

        repository.database.insertPublisher(publisher1)
        repository.database.insertPublisher(publisher2)
        repository.database.insertPublisher(publisher3)

        val publishersFromDb = repository.database.getAllPublishers()
        assertTrue( publishersFromDb.size == 3)
        assertContentEquals(publishersFromDb, listOf(publisher1, publisher2, publisher3))

        val actualPublisher2 = repository.database.getPublisherByUuid("dsdf-sdfsd-fsdf-publisher-2")
        assertEquals(actualPublisher2, publisher2)

        repository.database.deleteAllPublishers()
        assertTrue(repository.database.getAllPublishers().isEmpty())
    }
}