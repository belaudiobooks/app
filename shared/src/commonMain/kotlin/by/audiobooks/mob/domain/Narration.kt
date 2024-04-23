package by.audiobooks.mob.domain

import kotlinx.datetime.LocalDate

data class Narration(
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
    val narratorUuids: List<String> = emptyList(),
    val publisherUuids: List<String> = emptyList(),
    val translatorUuids: List<String> = emptyList()
)
