package ru.drweb.test.features.root.presentation

import ru.drweb.test.core.message.presentation.FakeMessageComponent
import ru.drweb.test.core.utils.createFakeChildStackStateFlow
import ru.drweb.test.features.home.presentation.FakeHomeComponent

class FakeRootComponent : RootComponent {

    override val childStack = createFakeChildStackStateFlow(
        RootComponent.Child.Home(FakeHomeComponent())
    )

    override val messageComponent = FakeMessageComponent()
}
