package ru.drweb.test.core

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.network.AndroidNetworkConnectivityProvider
import me.aartikov.replica.network.NetworkConnectivityProvider
import org.koin.core.component.get
import org.koin.dsl.module
import ru.drweb.test.core.activity.ActivityProvider
import ru.drweb.test.core.debug_tools.DebugTools
import ru.drweb.test.core.debug_tools.RealDebugTools
import ru.drweb.test.core.error_handling.ErrorHandler
import ru.drweb.test.core.message.data.MessageService
import ru.drweb.test.core.message.data.MessageServiceImpl
import ru.drweb.test.core.message.presentation.MessageComponent
import ru.drweb.test.core.message.presentation.RealMessageComponent
import ru.drweb.test.core.permissions.PermissionService

val coreModule = module {
    single { ActivityProvider() }
    single<NetworkConnectivityProvider> { AndroidNetworkConnectivityProvider(get()) }
    single { ReplicaClient(get()) }
    single<MessageService> { MessageServiceImpl() }
    single { ErrorHandler(get()) }
    single<DebugTools> { RealDebugTools(get()) }
    single(createdAtStart = true) { PermissionService(get(), get()) }
}

fun ComponentFactory.createMessageComponent(
    componentContext: ComponentContext
): MessageComponent {
    return RealMessageComponent(componentContext, get())
}
