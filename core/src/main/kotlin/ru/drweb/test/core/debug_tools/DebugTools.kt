package ru.drweb.test.core.debug_tools

import okhttp3.Interceptor

interface DebugTools {

    val interceptors: List<Interceptor>

    fun launch()
}