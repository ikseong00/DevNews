package org.ikseong.devnews.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ikseong.devnews.data.local.dao.ReadHistoryDao
import org.ikseong.devnews.data.local.entity.toArticle
import org.ikseong.devnews.data.local.entity.toReadHistoryEntity
import org.ikseong.devnews.data.model.Article

class HistoryRepository(private val readHistoryDao: ReadHistoryDao) {

    fun getAll(): Flow<List<Article>> =
        readHistoryDao.getAll().map { entities -> entities.map { it.toArticle() } }

    suspend fun record(article: Article) {
        readHistoryDao.upsert(article.toReadHistoryEntity())
    }

    suspend fun deleteAll() {
        readHistoryDao.deleteAll()
    }
}
