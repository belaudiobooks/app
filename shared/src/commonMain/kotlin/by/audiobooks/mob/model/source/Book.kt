package by.audiobooks.mob.model.source

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val uuid: String,
    val title: String,
    val description: String,
    @SerialName("description_source")
    val descriptionSource: String,
    val authors: List<String>,
    val slug: String,
    val tag: List<Long>,
    val narrations: List<Narration>,
)
