package ru.drweb.test.features.home.presentation

import ru.drweb.test.core.utils.createFakeChildStackStateFlow
import ru.drweb.test.features.list.presentation.FakeListComponent

class FakeHomeComponent : HomeComponent {

    override val childStack = createFakeChildStackStateFlow(
        HomeComponent.Child.List(FakeListComponent())
    )
}
