package org.ikseong.devnews.ui.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.ikseong.devnews.data.repository.HistoryRepository

class HistoryViewModel(
    private val historyRepository: HistoryRepository,
) : ViewModel() {

    val uiState = historyRepository.getAll()
        .map { HistoryUiState(articles = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HistoryUiState(),
        )

    fun deleteAll() {
        viewModelScope.launch {
            historyRepository.deleteAll()
        }
    }
}
