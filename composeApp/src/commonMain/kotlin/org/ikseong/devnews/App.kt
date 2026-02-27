package org.ikseong.devnews

import androidx.compose.runtime.Composable
import org.ikseong.devnews.navigation.AppNavigation
import org.ikseong.devnews.ui.theme.DevNewsTheme

@Composable
fun App() {
    DevNewsTheme {
        AppNavigation()
    }
}
