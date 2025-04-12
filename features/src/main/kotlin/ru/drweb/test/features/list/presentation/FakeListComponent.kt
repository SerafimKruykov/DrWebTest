package ru.drweb.test.features.list.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import ru.drweb.test.core.utils.LoadableState
import ru.drweb.test.core.common_domain.AppModel
import ru.drweb.test.core.common_domain.PackageName

class FakeListComponent : ListComponent {

    override val appsState = MutableStateFlow(
        listOf(
            AppModel(
                packageName = PackageName("1"),
                name = "Telegram",
                image = null
            ),
            AppModel(
                packageName = PackageName("5"),
                name = "hh",
                image = null
            ),
            AppModel(
                packageName = PackageName("7"),
                name = "linkedin",
                image = null
            )
        )
    )

    override fun onAppClick(packageName: PackageName) = Unit
}