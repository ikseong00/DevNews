package org.ikseong.devnews.ui.screen.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.ikseong.devnews.ui.component.WebView
import org.ikseong.devnews.util.openUrl
import org.ikseong.devnews.util.shareUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    link: String,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로가기",
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { openUrl(link) }) {
                        Icon(
                            imageVector = Icons.Filled.OpenInBrowser,
                            contentDescription = "외부 브라우저로 열기",
                        )
                    }
                    IconButton(onClick = { shareUrl(link) }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "공유",
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        WebView(
            url = link,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}
