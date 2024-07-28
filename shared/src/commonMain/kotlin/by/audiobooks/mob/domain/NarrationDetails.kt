package by.audiobooks.mob.domain

import kotlinx.datetime.LocalDate

data class NarrationDetails(
    val uuid: String,
    val bookUuid: String,
    val duration: Double,
    val paid: Boolean,
    val language: Language,
    val coverImage: String,
    val coverImageSource: String,
    val date: LocalDate,
    val description: String,
    val previewUrl: String,
    val links: List<LinkDetails> = emptyList(),
    val narrators: List<Person> = emptyList(),
    val publishers: List<Publisher> = emptyList(),
    val translators: List<Person> = emptyList()
)
