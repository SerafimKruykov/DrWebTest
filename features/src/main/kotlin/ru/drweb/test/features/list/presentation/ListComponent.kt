package ru.drweb.test.features.list.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.drweb.test.core.utils.LoadableState
import ru.drweb.test.core.common_domain.AppModel
import ru.drweb.test.core.common_domain.PackageName

interface ListComponent {

    val appsState: StateFlow<List<AppModel>>

    fun onAppClick(packageName: PackageName)

    sealed interface Output {
        data class DetailsRequested(val packageName: PackageName) : Output
    }
}
