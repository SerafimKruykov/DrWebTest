package ru.drweb.test.features.home

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import org.koin.dsl.module
import ru.drweb.test.core.ComponentFactory
import ru.drweb.test.features.home.data.AppsRepository
import ru.drweb.test.features.home.data.AppsRepositoryImpl
import ru.drweb.test.core.common_domain.PackageName
import ru.drweb.test.features.home.presentation.HomeComponent
import ru.drweb.test.features.home.presentation.RealHomeComponent
import ru.drweb.test.features.details.presentation.AppDetailsComponent
import ru.drweb.test.features.details.presentation.RealAppDetailsComponent
import ru.drweb.test.features.list.presentation.ListComponent
import ru.drweb.test.features.list.presentation.RealListComponent

val homeModule = module {
    single<AppsRepository> { AppsRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createHomeComponent(
    componentContext: ComponentContext
): HomeComponent {
    return RealHomeComponent(componentContext, get())
}

fun ComponentFactory.createAppsListComponent(
    componentContext: ComponentContext,
    onOutput: (ListComponent.Output) -> Unit
): ListComponent {
    return RealListComponent(componentContext, onOutput, get(), get())
}

fun ComponentFactory.createAppDetailsComponent(
    componentContext: ComponentContext,
    packageName: PackageName
): AppDetailsComponent {
    return RealAppDetailsComponent(componentContext, packageName, get(), get(), get(), get())
}
