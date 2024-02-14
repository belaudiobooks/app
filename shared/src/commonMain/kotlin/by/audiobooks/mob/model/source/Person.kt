package by.audiobooks.mob.model.source

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val uuid: String,
    val name: String,
    val description: String,
    @SerialName("description_source")
    val descriptionSource: String,
    val photo: String = "",
    @SerialName("photo_source")
    val photoSource: String,
    val slug: String,
    val gender: Gender
)
