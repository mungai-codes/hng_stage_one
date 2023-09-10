package com.mungai_codes.hngstageone.presentation.github

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun GithubScreen() {

    val profileUrl by remember {
        mutableStateOf("https://github.com/mungai-codes")
    }
    val isLoading = remember { mutableStateOf(true) }

    AndroidView(factory = { context ->
        WebView(context).apply {

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.javaScriptEnabled = true

            webViewClient = object : WebViewClient() {

                override fun onPageStarted(
                    view: WebView, url: String,
                    favicon: Bitmap?
                ) {
                    isLoading.value = true
                }

                override fun onPageFinished(
                    view: WebView, url: String
                ) {
                    isLoading.value = false
                }
            }

            loadUrl(profileUrl)
        }
    }, update = { webView ->
        webView.loadUrl(profileUrl)
    })

    if (isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LinearProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }

}