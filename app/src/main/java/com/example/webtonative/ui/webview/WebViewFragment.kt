package com.example.webtonative.ui.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.webtonative.R
import com.google.android.material.appbar.MaterialToolbar

class WebViewFragment : Fragment(R.layout.fragment_webview) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString("url") ?: return

        val webView = view.findViewById<WebView>(R.id.webView)
        val tvUrl = view.findViewById<TextView>(R.id.tvUrl)

        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                tvUrl.text = url
            }
        }

        webView.loadUrl(url)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbarWeb)
        toolbar.setNavigationOnClickListener {
            // Back button behavior: Retain URL in Home
            findNavController().previousBackStackEntry?.savedStateHandle?.set("last_url", url)
            findNavController().previousBackStackEntry?.savedStateHandle?.set("clear_url", false)
            findNavController().popBackStack()
        }

        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_close) {
                // Close button behavior: Clear URL in Home
                findNavController().previousBackStackEntry?.savedStateHandle?.set("clear_url", true)
                findNavController().previousBackStackEntry?.savedStateHandle?.set("last_url", "")
                findNavController().popBackStack(R.id.homeFragment, false)
            }
            true
        }
    }
}
