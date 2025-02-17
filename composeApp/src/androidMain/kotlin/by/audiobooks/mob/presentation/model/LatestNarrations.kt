package by.audiobooks.mob.presentation.model

import by.audiobooks.mob.domain.BookCover
import by.audiobooks.mob.presentation.ViewState

data class LatestNarrations(
    val narrations: ViewState<List<BookCover>> = ViewState.Loading
)
