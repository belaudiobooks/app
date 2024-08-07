package by.audiobooks.mob.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkDTO(
    val url: String,
    @SerialName("url_type")
    val urlType: Long
)
