package by.audiobooks.mob.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: Long,
    val name: String,
    val slug: String,
    val description: String
)
