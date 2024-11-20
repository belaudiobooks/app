package by.audiobooks.mob.domain

data class PersonsBooksDetails(
    val authoredBooksDetails: List<BookDetails>,
    val translatedBooksDetails: List<BookDetails>,
    val narratedBooksDetails: List<BookDetails>
)
