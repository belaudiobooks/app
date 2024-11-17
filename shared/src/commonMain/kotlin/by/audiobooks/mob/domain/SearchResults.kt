package by.audiobooks.mob.domain

data class SearchResults(
    val bookHits: List<BookDetails>,
    val publisherHits: List<Publisher>,
    val personHits: List<Person>
)
