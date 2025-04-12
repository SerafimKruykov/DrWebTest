package ru.drweb.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import com.arkivanov.essenty.lifecycle.asEssentyLifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import ru.drweb.test.core.ComponentFactory
import ru.drweb.test.core.activity.ActivityProvider
import ru.drweb.test.core.koin
import ru.drweb.test.core.theme.AppTheme
import ru.drweb.test.features.root.createRootComponent
import ru.drweb.test.features.root.presentation.RootUi

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val activityProvider = application.koin.get<ActivityProvider>()
        activityProvider.attachActivity(this)
        lifecycle.asEssentyLifecycle().doOnDestroy {
            activityProvider.detachActivity()
        }

        val rootComponent = retainedComponent { componentContext ->
            val componentFactory = application.koin.get<ComponentFactory>()
            componentFactory.createRootComponent(componentContext)
        }

        setContent {
            AppTheme {
                RootUi(rootComponent)
            }
        }
    }
}
