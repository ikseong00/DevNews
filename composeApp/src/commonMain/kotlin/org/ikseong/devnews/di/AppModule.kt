package org.ikseong.devnews.di

import org.ikseong.devnews.data.remote.SupabaseProvider
import org.koin.dsl.module

val dataModule = module {
    single { SupabaseProvider.client }
}

val viewModelModule = module {
}
