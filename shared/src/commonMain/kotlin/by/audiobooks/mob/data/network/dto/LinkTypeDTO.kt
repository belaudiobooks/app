package by.audiobooks.mob.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LinkTypeDTO(
    val id: Long,
    val name: String,
    val caption: String,
    val icon: String,
    val availability: AvailabilityDTO
)
