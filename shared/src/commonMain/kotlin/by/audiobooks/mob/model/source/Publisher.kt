package by.audiobooks.mob.model.source

import kotlinx.serialization.Serializable

@Serializable
data class Publisher(
    val uuid: String,
    val name: String,
    val slug: String,
    val url: String,
    val logo: String,
    val description: String
)
