package by.audiobooks.mob.domain

data class Tag(
    val id: Long,
    val name: String,
    val description: String,
    val bookCount: Long
)
