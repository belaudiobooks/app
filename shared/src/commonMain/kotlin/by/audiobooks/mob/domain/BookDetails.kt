package by.audiobooks.mob.domain

data class BookDetails(
    val uuid: String,
    val title: String,
    val description: String,
    val descriptionSource: String,
    val narrations: List<NarrationDetails>,
    val authors: List<Person>,
    val tags: List<Tag>
)
