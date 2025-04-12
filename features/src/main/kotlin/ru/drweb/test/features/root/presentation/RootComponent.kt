package ru.drweb.test.features.root.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.drweb.test.core.message.presentation.MessageComponent
import ru.drweb.test.features.home.presentation.HomeComponent

/**
 * A root of a Decompose component tree.
 *
 * Note: Try to minimize child count in RootComponent. It should operate by flows of screens rather than separate screens.
 */
interface RootComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    val messageComponent: MessageComponent

    sealed interface Child {
        class Home(val component: HomeComponent) : Child
    }
}
