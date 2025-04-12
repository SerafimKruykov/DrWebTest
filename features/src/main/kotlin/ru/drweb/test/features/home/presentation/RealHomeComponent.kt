package ru.drweb.test.features.home.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import kotlinx.serialization.Serializable
import ru.drweb.test.core.ComponentFactory
import ru.drweb.test.core.utils.toStateFlow
import ru.drweb.test.features.home.createAppDetailsComponent
import ru.drweb.test.features.home.createAppsListComponent
import ru.drweb.test.core.common_domain.PackageName
import ru.drweb.test.features.list.presentation.ListComponent

class RealHomeComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, HomeComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.List,
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): HomeComponent.Child = when (config) {
        is ChildConfig.List -> {
            HomeComponent.Child.List(
                componentFactory.createAppsListComponent(
                    componentContext,
                    ::onAppsListOutput
                )
            )
        }

        is ChildConfig.Details -> {
            HomeComponent.Child.Details(
                componentFactory.createAppDetailsComponent(
                    componentContext,
                    config.packageName
                )
            )
        }
    }

    private fun onAppsListOutput(output: ListComponent.Output) {
        when (output) {
            is ListComponent.Output.DetailsRequested -> {
                navigation.push(ChildConfig.Details(output.packageName))
            }
        }
    }

    @Serializable
    sealed interface ChildConfig {

        @Serializable
        data object List : ChildConfig

        @Serializable
        data class Details(val packageName: PackageName) : ChildConfig
    }
}
