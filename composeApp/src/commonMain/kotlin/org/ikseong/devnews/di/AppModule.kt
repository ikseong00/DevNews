package org.ikseong.devnews.di

import org.ikseong.devnews.data.remote.SupabaseProvider
import org.ikseong.devnews.data.repository.ArticleRepository
import org.koin.dsl.module

val dataModule = module {
    single { SupabaseProvider.client }
    single { ArticleRepository(get()) }
}

val viewModelModule = module {
}
