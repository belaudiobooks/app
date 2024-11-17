package by.audiobooks.mob.data.network.dto

data class SearchResultDTO(
    val model: SearchResultModel,
    val objectId: String
)

enum class SearchResultModel(val key: String) {
    BOOK("book"),
    PUBLISHER("publisher"),
    PERSON("person"),
}
