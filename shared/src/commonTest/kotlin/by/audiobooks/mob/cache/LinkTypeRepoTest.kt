package by.audiobooks.mob.cache

import by.audiobooks.mob.model.source.Availability
import by.audiobooks.mob.model.source.LinkType
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LinkTypeRepoTest {

    private val repository = getTestRepository()

    @Test
    fun linkTypeRepoTest() = runTest {
        assertTrue(repository.database.getAllLinkTypes().isEmpty())

        val linkType1 = LinkType(id = 1L, name = "Test LinkType 1", caption = "LinkType caption 1",
            icon = "https://icon-linktype-1.ico", availability = Availability.EVERYWHERE)
        val linkType2 = LinkType(id = 2L, name = "Test LinkType 2", caption = "LinkType caption 2",
            icon = "https://icon-linktype-2.ico", availability = Availability.UNAVAILABLE_IN_BELARUS)
        val linkType3 = LinkType(id = 3L, name = "Test LinkType 3", caption = "LinkType caption 3",
            icon = "https://icon-linktype-3.ico", availability = Availability.USA_ONLY)

        repository.database.insertLinkType(linkType1)
        repository.database.insertLinkType(linkType2)
        repository.database.insertLinkType(linkType3)

        val linkTypesFromDb = repository.database.getAllLinkTypes()
        assertTrue( linkTypesFromDb.size == 3)
        assertContentEquals(linkTypesFromDb, listOf(linkType1, linkType2, linkType3))

        val actualLinkType2 = repository.database.getLinkTypeById(2)
        assertEquals(actualLinkType2, linkType2)

        repository.database.deleteAllLinkTypes()
        assertTrue(repository.database.getAllLinkTypes().isEmpty())
    }
}