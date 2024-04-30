package by.audiobooks.mob.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.audiobooks.mob.data.Repository
import by.audiobooks.mob.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class HomeViewModel(
    val repository: Repository
) : ViewModel() {

    val uiState: StateFlow<HomeScreenUiState> = repository.getAllBooks()
        .map { list -> HomeScreenUiState.Success(list) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = HomeScreenUiState.Loading(),
        )

    fun reloadData() {
        viewModelScope.launch {
            repository.refreshData()
        }
    }

}


sealed class HomeScreenUiState {
    data class Loading(val books: List<Book> = emptyList()): HomeScreenUiState()
    data class Success(val books: List<Book>): HomeScreenUiState()
    data class Error(val exception: Throwable): HomeScreenUiState()
}