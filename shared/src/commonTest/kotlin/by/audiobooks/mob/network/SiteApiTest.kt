package by.audiobooks.mob.network

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SiteApiTest {

    @Test
    fun downloadDataTest() = runTest {

        val data = SiteApi().downloadData()

        assertTrue {
            data.books.isNotEmpty() &&
            data.tags.isNotEmpty() &&
            data.people.isNotEmpty() &&
            data.linkTypes.isNotEmpty() &&
            data.publishers.isNotEmpty()
        }
    }

}