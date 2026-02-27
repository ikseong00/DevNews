package org.ikseong.devnews.ui.screen.history

import org.ikseong.devnews.data.model.Article

data class HistoryUiState(
    val articles: List<Article> = emptyList(),
)
