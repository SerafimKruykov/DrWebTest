package ru.drweb.test.core.common_domain

import android.graphics.drawable.Drawable

data class DetailedApp(
    val packageName: PackageName,
    val name: String,
    val image: Drawable?,
    val versionName: String,
    val checkSum: Long
)

data class DetailedAppWrapper(
    val app: DetailedApp?
) {
    companion object {
        val Empty = DetailedAppWrapper(null)
    }
}
