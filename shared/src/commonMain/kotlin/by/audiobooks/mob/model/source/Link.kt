package by.audiobooks.mob.model.source

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Link(
    val url: String,
    @SerialName("url_type")
    val urlType: Long
)
