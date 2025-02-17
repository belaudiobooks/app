package by.audiobooks.mob.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.audiobooks.mob.data.Repository
import by.audiobooks.mob.presentation.ViewState
import by.audiobooks.mob.presentation.model.LatestNarrations
import by.audiobooks.mob.presentation.model.SectionContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    companion object {
        val topCategoryIds = arrayOf(
            2L, // sucasnaja-proza
            7L, // klasiki-bielaruskaj-litaratury
            9L // dzieciam-i-padletkam
        )
    }

    private val _latestNarrations: MutableStateFlow<LatestNarrations> = MutableStateFlow(LatestNarrations())
    val newNarrations = _latestNarrations.asStateFlow()
    private val _topCategories: MutableStateFlow<Map<Long,ViewState<SectionContent>>> = MutableStateFlow(
        topCategoryIds.associate { Pair(it, ViewState.Loading) }
    )
    val topCategories = _topCategories.asStateFlow()

    init {
        getNewNarrations()
        getTopCategories()
    }

    private fun getNewNarrations() {
        repository.getNLatestNarrationsAsBookCovers(11)
            .onEach {
                _latestNarrations.value = LatestNarrations(narrations = ViewState.Success(it))
            }
            .catch {
                _latestNarrations.value = LatestNarrations(narrations = ViewState.Error(it.message))
            }
            .launchIn(viewModelScope)
    }

    private fun getTopCategories() {
        topCategoryIds.map { tagId ->
            repository.getTagById(tagId).combine(
                repository.getBooksDetailsByTagId(tagId)
            ) {
                tag, books ->
                SectionContent(
                    tag = tag,
                    books = books
                )
            }
        }
            .merge()
            .scan(mutableMapOf<Long, ViewState<SectionContent>>()) { acc, cat ->
                acc[cat.tag.id] = ViewState.Success(cat)
                acc
            }
            .onEach {
                _topCategories.value = it
            }
            .catch { err ->
                _topCategories.value = topCategoryIds.associate { Pair(it, ViewState.Error(err.message)) }
            }
            .launchIn(viewModelScope)
    }

}
