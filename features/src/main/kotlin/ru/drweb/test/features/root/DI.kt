package ru.drweb.test.features.root

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import ru.drweb.test.core.ComponentFactory
import ru.drweb.test.features.root.presentation.RealRootComponent
import ru.drweb.test.features.root.presentation.RootComponent

fun ComponentFactory.createRootComponent(componentContext: ComponentContext): RootComponent {
    return RealRootComponent(componentContext, get())
}