package by.audiobooks.mob.domain

import kotlinx.datetime.LocalDate

data class BookCover(
    val uuid: String,
    val title: String,
    val coverImage: String,
    val date: LocalDate,
    val description: String,
    val descriptionSource: String,
    val authors: List<Person>,
    val tags: List<Tag>
)
