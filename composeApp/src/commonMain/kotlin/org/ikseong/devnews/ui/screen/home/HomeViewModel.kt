package org.ikseong.devnews.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ikseong.devnews.data.model.ArticleCategory
import org.ikseong.devnews.data.repository.ArticleRepository

@OptIn(FlowPreview::class)
class HomeViewModel(
    private val articleRepository: ArticleRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val searchQueryFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    private var currentPage = 0

    init {
        loadArticles()
        observeSearchQuery()
    }

    fun loadArticles() {
        currentPage = 0
        _uiState.update { it.copy(isLoading = true, error = null, hasMorePages = true) }

        viewModelScope.launch {
            runCatching {
                fetchArticles(offset = 0)
            }.onSuccess { articles ->
                _uiState.update {
                    it.copy(
                        articles = articles,
                        isLoading = false,
                        hasMorePages = articles.size >= ArticleRepository.DEFAULT_PAGE_SIZE,
                    )
                }
            }.onFailure { e ->
                _uiState.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    fun loadMore() {
        val state = _uiState.value
        if (state.isLoading || state.isLoadingMore || !state.hasMorePages) return

        currentPage++
        _uiState.update { it.copy(isLoadingMore = true) }

        viewModelScope.launch {
            runCatching {
                fetchArticles(offset = currentPage * ArticleRepository.DEFAULT_PAGE_SIZE)
            }.onSuccess { articles ->
                _uiState.update {
                    it.copy(
                        articles = it.articles + articles,
                        isLoadingMore = false,
                        hasMorePages = articles.size >= ArticleRepository.DEFAULT_PAGE_SIZE,
                    )
                }
            }.onFailure { e ->
                currentPage--
                _uiState.update {
                    it.copy(isLoadingMore = false, error = e.message)
                }
            }
        }
    }

    fun selectCategory(category: ArticleCategory?) {
        if (_uiState.value.selectedCategory == category) return
        _uiState.update { it.copy(selectedCategory = category) }
        loadArticles()
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        searchQueryFlow.tryEmit(query)
    }

    fun toggleSearch() {
        val isActive = !_uiState.value.isSearchActive
        _uiState.update {
            it.copy(
                isSearchActive = isActive,
                searchQuery = if (!isActive) "" else it.searchQuery,
            )
        }
        if (!isActive) {
            loadArticles()
        }
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isBlank()) {
                        loadArticles()
                    } else {
                        searchArticles(query)
                    }
                }
        }
    }

    private fun searchArticles(query: String) {
        currentPage = 0
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            runCatching {
                articleRepository.searchArticles(query)
            }.onSuccess { articles ->
                _uiState.update {
                    it.copy(
                        articles = articles,
                        isLoading = false,
                        hasMorePages = false,
                    )
                }
            }.onFailure { e ->
                _uiState.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    private suspend fun fetchArticles(offset: Int) =
        articleRepository.getArticles(
            category = _uiState.value.selectedCategory,
            offset = offset,
        )
}
