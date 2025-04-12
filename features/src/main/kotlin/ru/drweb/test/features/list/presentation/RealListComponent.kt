package ru.drweb.test.features.list.presentation

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.drweb.test.core.common_domain.PackageName
import ru.drweb.test.core.error_handling.ErrorHandler
import ru.drweb.test.core.utils.componentScope
import ru.drweb.test.core.utils.computed
import ru.drweb.test.core.utils.observe
import ru.drweb.test.features.home.data.AppsRepository


class RealListComponent(
    componentContext: ComponentContext,
    private val onOutput: (ListComponent.Output) -> Unit,
    appsRepository: AppsRepository,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, ListComponent {

    private val appsReplica = appsRepository
        .appsReplica
        .observe(this, errorHandler)

    override val appsState = computed(appsReplica) {
        it.data ?: emptyList()
    }

    override fun onAppClick(packageName: PackageName) {
        onOutput(ListComponent.Output.DetailsRequested(packageName))
    }
}
