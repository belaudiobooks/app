package by.audiobooks.mob.domain

data class LinkDetails(
    val narrationUuid: String,
    val url: String,
    val linkTypeId: Long,
    val linkTypeName: String,
    val linkTypeCaption: String,
    val linkTypeIcon: String,
    val linkTypeAvailability: Availability
)
