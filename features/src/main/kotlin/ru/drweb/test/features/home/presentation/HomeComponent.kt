package ru.drweb.test.features.home.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.drweb.test.features.details.presentation.AppDetailsComponent
import ru.drweb.test.features.list.presentation.ListComponent

interface HomeComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class List(val component: ListComponent) : Child
        class Details(val component: AppDetailsComponent) : Child
    }
}
