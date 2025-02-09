package by.audiobooks.mob.presentation.model

import by.audiobooks.mob.domain.BookDetails
import by.audiobooks.mob.domain.Tag

data class SectionContent(
    val tag: Tag,
    val books: List<BookDetails>
)

