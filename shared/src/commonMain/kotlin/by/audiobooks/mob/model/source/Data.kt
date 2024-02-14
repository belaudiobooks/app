package by.audiobooks.mob.model.source

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val books: List<Book>,
    val people: List<Person>,
    @SerialName("link_types")
    val linkTypes: List<LinkType>,
    val tags: List<Tag>,
    val publishers: List<Publisher>
)
