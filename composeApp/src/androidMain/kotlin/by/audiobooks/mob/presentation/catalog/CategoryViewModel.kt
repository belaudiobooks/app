package by.audiobooks.mob.presentation.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.audiobooks.mob.data.Repository
import by.audiobooks.mob.presentation.ViewState
import by.audiobooks.mob.presentation.model.SectionContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CategoryViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _sectionContent: MutableStateFlow<ViewState<SectionContent>> = MutableStateFlow(ViewState.Loading)
    val sectionContent = _sectionContent.asStateFlow()

    fun getCatalogContent(tagId: Long) {
        repository.getTagById(tagId = tagId)
            .combine(
                repository.getBooksDetailsByTagId(id = tagId)
            ) {
                tag, books ->
                SectionContent(
                    tag = tag,
                    books = books
                )
            }
            .onEach {
                _sectionContent.value = ViewState.Success(it)
            }
            .catch {
                _sectionContent.value = ViewState.Error(it.message)
            }
            .launchIn(viewModelScope)
    }
}
