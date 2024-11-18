package by.audiobooks.mob.data.network

import by.audiobooks.mob.data.network.dto.SearchResultDTO
import by.audiobooks.mob.data.network.dto.SearchResultModel
import com.algolia.client.api.SearchClient
import com.algolia.client.model.search.SearchParamsObject

class AlgoliaSearchApi {

    companion object {
        private const val APP_ID = "1RL4DSNIMX"
        private const val API_KEY = "4c0874dbf4fd2dc5f079657d5702da65"
        private const val INDEX_NAME = "prod"
    }

    private val client = SearchClient(APP_ID, API_KEY)
    private val allowedModels: List<String> = SearchResultModel.entries.map { it.key }

    suspend fun search(query: String): List<SearchResultDTO> {
        val response = client.searchSingleIndex(
            indexName = INDEX_NAME,
            searchParams = SearchParamsObject(
                query = query,
            )
        )
        return response.hits.filter {
            it.additionalProperties?.containsKey("model") ?: false
        }.filter { hit ->
            allowedModels.any { it.equals(hit.additionalProperties!!["model"].toString().trim('"'), ignoreCase = true) }
        }.map {
            SearchResultDTO(
                model = SearchResultModel.entries
                    .first { model -> model.key.equals(it.additionalProperties!!["model"].toString().trim('"'), ignoreCase = true) },
                objectId = it.objectID
            )
        }
    }
}
