package ru.drweb.test.core.utils

import android.content.Intent

fun Intent.addNewTaskFlag(): Intent {
    return apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
}
