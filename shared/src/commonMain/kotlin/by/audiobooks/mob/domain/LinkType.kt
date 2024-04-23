package by.audiobooks.mob.domain

data class LinkType(
    val id: Long,
    val name: String,
    val caption: String,
    val icon: String,
    val availability: Availability
)
