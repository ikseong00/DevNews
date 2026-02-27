package org.ikseong.devnews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.ikseong.devnews.ui.theme.DevNewsTheme

@Composable
fun App() {
    DevNewsTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .safeContentPadding()
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "DevNews",
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
