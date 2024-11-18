package by.audiobooks.mob.data.network

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AlgoliaSearchApiTest {

    @Test
    fun searchTest() = runTest {
        val searchClient = AlgoliaSearchApi()
        val response = searchClient.search("яну")
        assertTrue { response.isNotEmpty() }
    }

    @Test
    fun searchEmptyTest() = runTest {
        val searchClient = AlgoliaSearchApi()
        val response = searchClient.search("цв3ок489го3о40г2к98го349вк82г3984вк")
        assertTrue { response.isEmpty() }
    }
}
