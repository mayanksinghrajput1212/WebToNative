package com.example.webtonative.utils

import android.util.Patterns

object UrlUtils {

    fun validateUrl(input: String): String? {
        val text = input.trim()
        if (text.isEmpty()) return null

        val finalUrl =
            if (!text.startsWith("http"))
                "https://$text"
            else text

        return if (Patterns.WEB_URL.matcher(finalUrl).matches())
            finalUrl else null
    }
}
