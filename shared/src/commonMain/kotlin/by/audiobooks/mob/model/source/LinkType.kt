package by.audiobooks.mob.model.source

import kotlinx.serialization.Serializable

@Serializable
data class LinkType(
    val id: Long,
    val name: String,
    val caption: String,
    val icon: String,
    val availability: Availability
)
