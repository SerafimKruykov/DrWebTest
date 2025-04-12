package ru.drweb.test.features.details.presentation

import android.app.Activity
import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.resources.desc.StringDesc
import me.aartikov.replica.algebra.normal.withKey
import ru.drweb.test.core.error_handling.ErrorHandler
import ru.drweb.test.core.message.data.MessageService
import ru.drweb.test.core.message.domain.Message
import ru.drweb.test.core.utils.observe
import ru.drweb.test.features.home.data.AppsRepository
import ru.drweb.test.core.common_domain.PackageName
import ru.drweb.test.core.utils.Resource
import ru.drweb.test.core.R
import ru.drweb.test.core.activity.ActivityProvider
import ru.drweb.test.core.utils.addNewTaskFlag
import ru.drweb.test.core.utils.computed

class RealAppDetailsComponent(
    componentContext: ComponentContext,
    packageName: PackageName,
    appsRepository: AppsRepository,
    private val messageService: MessageService,
    private val activityProvider: ActivityProvider,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, AppDetailsComponent {

    private val appReplica = appsRepository
        .appByIdReplica
        .withKey(packageName)
        .observe(this, errorHandler)

    override val detailedAppState = computed(appReplica) {
        it.data?.app
    }

    override fun onOpenAppClick() {
        val activity = activityProvider.activity ?: run {
            showError()
            return
        }

        val packageName = detailedAppState.value?.packageName?.value ?: run {
            showError()
            return
        }

        tryOpenApp(activity, packageName)
    }

    private fun tryOpenApp(activity: Activity, packageName: String) {
        try {
            val intent = activity.packageManager.getLaunchIntentForPackage(packageName)
                ?.addNewTaskFlag()
                ?: run {
                    showError()
                    return
                }

            activity.startActivity(intent)
        } catch (e: Exception) {
            showError()
        }
    }

    private fun showError() {
        messageService.showMessage(
            Message(
                text = StringDesc.Resource(R.string.error_unexpected)
            )
        )
    }
}
