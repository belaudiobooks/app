package by.audiobooks.mob.domain

data class Book(
    val uuid: String,
    val title: String,
    val description: String,
    val descriptionSource: String,
    val authorUuids: List<String>,
    val tagIds: List<Long>
)
