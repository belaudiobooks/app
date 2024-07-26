package by.audiobooks.mob.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class PublisherDTO(
    val uuid: String,
    val name: String,
    val slug: String,
    val url: String,
    val logo: String,
    val description: String
)
