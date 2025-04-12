package ru.drweb.test.features.details.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.drweb.test.core.common_domain.DetailedApp
import ru.drweb.test.core.common_domain.PackageName
import ru.drweb.test.core.utils.LoadableState
import ru.drweb.test.core.common_domain.DetailedAppWrapper

interface AppDetailsComponent {

    val detailedAppState: StateFlow<DetailedApp?>

    fun onOpenAppClick()
}
