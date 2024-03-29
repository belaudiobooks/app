package by.audiobooks.mob.model.source

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: Long,
    val name: String,
    val slug: String,
    val description: String
)
