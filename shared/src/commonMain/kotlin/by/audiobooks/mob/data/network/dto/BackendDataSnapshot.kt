package by.audiobooks.mob.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BackendDataSnapshot(
    val books: List<BookDTO>,
    val people: List<PersonDTO>,
    @SerialName("link_types")
    val linkTypes: List<LinkTypeDTO>,
    val tags: List<TagDTO>,
    val publishers: List<PublisherDTO>
)
