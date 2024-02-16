package by.audiobooks.mob.cache

import by.audiobooks.mob.model.source.Tag
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TagRepoTest {

    private val repository = getTestRepository()

    @Test
    fun tagRepoTest() = runTest {
        assertTrue(repository.database.getAllTags().isEmpty())

        val tag1 = Tag(id = 1L, name = "Test tag 1", slug = "test-tag-1", description = "1")
        val tag2 = Tag(id = 2L, name = "Test tag 2", slug = "test-tag-2", description = "2")
        val tag3 = Tag(id = 3L, name = "Test tag 3", slug = "test-tag-3", description = "3")

        repository.database.insertTag(tag1)
        repository.database.insertTag(tag2)
        repository.database.insertTag(tag3)

        val tagsFromDb = repository.database.getAllTags()
        assertTrue( tagsFromDb.size == 3)
        assertContentEquals(tagsFromDb, listOf(tag1, tag2, tag3))

        val actualTag2 = repository.database.getTagById(2)
        assertEquals(actualTag2, tag2)

        repository.database.deleteAllTags()
        assertTrue(repository.database.getAllTags().isEmpty())
    }
}