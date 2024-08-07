package by.audiobooks.mob.data.network.dto

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NarrationDTO(
    val uuid: String,
    val narrators: List<String>,
    val links: List<LinkDTO>,
    val duration: Double = 0.0,
    val publishers: List<String>,
    val paid: Boolean,
    val language: LanguageDTO,
    val translators: List<String>,
    @SerialName("cover_image")
    val coverImage: String = "",
    @SerialName("cover_image_source")
    val coverImageSource: String,
    val date: LocalDate,
    val description: String,
    @SerialName("preview_url")
    val previewUrl: String
)
