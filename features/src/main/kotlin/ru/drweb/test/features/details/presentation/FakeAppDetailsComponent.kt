package ru.drweb.test.features.details.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import ru.drweb.test.core.utils.LoadableState
import ru.drweb.test.core.common_domain.DetailedApp
import ru.drweb.test.core.common_domain.DetailedAppWrapper
import ru.drweb.test.core.common_domain.PackageName

class FakeAppDetailsComponent : AppDetailsComponent {

    override val detailedAppState = MutableStateFlow(
        DetailedApp(
            packageName = PackageName("1"),
            name = "TestApp",
            image = null,
            versionName = "1.0",
            checkSum = 0L
        )
    )

    override fun onOpenAppClick() = Unit
}
