package ru.drweb.test.features.home.data

import android.content.pm.PackageManager
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedPhysicalReplica
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.PhysicalReplica
import me.aartikov.replica.single.ReplicaSettings
import ru.drweb.test.core.activity.ActivityProvider
import ru.drweb.test.core.common_domain.DetailedApp
import ru.drweb.test.core.common_domain.AppModel
import ru.drweb.test.core.common_domain.DetailedAppWrapper
import ru.drweb.test.core.common_domain.PackageName
import java.io.File
import java.security.MessageDigest
import kotlin.time.Duration.Companion.seconds

class AppsRepositoryImpl(
    replicaClient: ReplicaClient,
    private val activityProvider: ActivityProvider
) : AppsRepository {

    override val appsReplica: PhysicalReplica<List<AppModel>> =
        replicaClient.createReplica(
            name = "apps",
            settings = ReplicaSettings(
                staleTime = 10.seconds,
                clearTime = 60.seconds
            )
        ) {
            getInstalledApps()
        }

    override val appByIdReplica: KeyedPhysicalReplica<PackageName, DetailedAppWrapper> =
        replicaClient.createKeyedReplica(
            name = "app detailed",
            childName = { appId -> "appId = ${appId.value}" },
            settings = KeyedReplicaSettings(maxCount = 5),
            childSettings = {
                ReplicaSettings(staleTime = 10.seconds)
            }
        ) { packageName ->
            getDetailedApp(packageName)
        }

    private fun getInstalledApps(): List<AppModel> {
        val packageManager = activityProvider.activity?.packageManager ?: return emptyList()
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            .map { appData ->
                AppModel(
                    name = appData.loadLabel(packageManager).toString(),
                    image = appData.loadIcon(packageManager),
                    packageName = PackageName(appData.packageName),
                )
            }
    }

    private fun getDetailedApp(packageName: PackageName): DetailedAppWrapper {
        return try {
            val packageManager = activityProvider.activity?.packageManager
                ?: return DetailedAppWrapper.Empty
            val appInfo = packageManager.getApplicationInfo(
                packageName.value,
                PackageManager.GET_META_DATA
            )
            val packageInfo = packageManager.getPackageInfo(packageName.value, 0)
            val path = appInfo.sourceDir

            DetailedAppWrapper(
                DetailedApp(
                    packageName = packageName,
                    name = appInfo.loadLabel(packageManager).toString(),
                    image = appInfo.loadIcon(packageManager),
                    versionName = packageInfo.versionName,
                    checkSum = calculateCheckSum(path)
                )
            )
        } catch (e: PackageManager.NameNotFoundException) {
            DetailedAppWrapper.Empty
        }
    }

    private fun calculateCheckSum(filePath: String): Long {
        val digest = MessageDigest.getInstance("SHA-256")
        val file = File(filePath)
        file.inputStream().use { input ->
            val buffer = ByteArray(8192)
            var bytesRead: Int
            while (input.read(buffer).also { bytesRead = it } != -1) {
                digest.update(buffer, 0, bytesRead)
            }
        }
        return digest.digest().joinToString("") { "%02x".format(it) }.hashCode().toLong()
    }
}
