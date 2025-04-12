package ru.drweb.test.core.common_domain

import android.graphics.drawable.Drawable
import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class PackageName(val value: String)

data class AppModel(
    val packageName: PackageName,
    val image: Drawable?,
    val name: String
)