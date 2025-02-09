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
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class CatalogViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _sectionsContent: MutableStateFlow<ViewState<List<SectionContent>>> = MutableStateFlow(ViewState.Loading)
    val sectionsContent = _sectionsContent.asStateFlow()

    init {
        getCatalogContent()
    }

    private fun getCatalogContent() {
        repository.getAllTags()
            .flatMapLatest { tags ->
                val sectionFlows = tags.map { tag ->
                    repository.getBooksDetailsByTagId(tag.id).map { books ->
                        SectionContent(
                            tag,
                            books
                        )
                    }
                }
                combine(sectionFlows) { it.toList() }
            }
            .onEach {
                _sectionsContent.value = ViewState.Success(it.sortedBy { it.tag.bookCount })
            }
            .catch {
                _sectionsContent.value = ViewState.Error(it.message)
            }
            .launchIn(viewModelScope)
    }
}
