package ru.drweb.test.features.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import ru.drweb.test.core.ComponentFactory
import ru.drweb.test.core.createMessageComponent
import ru.drweb.test.core.utils.toStateFlow
import ru.drweb.test.features.home.createHomeComponent

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Home,
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    override val messageComponent = componentFactory.createMessageComponent(
        childContext(key = "message")
    )

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        is ChildConfig.Home -> {
            RootComponent.Child.Home(
                componentFactory.createHomeComponent(componentContext)
            )
        }
    }

    @Serializable
    sealed interface ChildConfig {

        @Serializable
        data object Home : ChildConfig
    }
}
