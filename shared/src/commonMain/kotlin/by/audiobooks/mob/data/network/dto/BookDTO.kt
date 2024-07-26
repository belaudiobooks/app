package by.audiobooks.mob.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDTO(
    val uuid: String,
    val title: String,
    val description: String,
    @SerialName("description_source")
    val descriptionSource: String,
    val authors: List<String>,
    val slug: String,
    val tag: List<Long>,
    val narrations: List<NarrationDTO>,
)
