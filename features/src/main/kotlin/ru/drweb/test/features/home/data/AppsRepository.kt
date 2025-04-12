package ru.drweb.test.features.home.data

import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.Replica
import ru.drweb.test.core.common_domain.AppModel
import ru.drweb.test.core.common_domain.PackageName
import ru.drweb.test.core.common_domain.DetailedAppWrapper

interface AppsRepository {

    val appsReplica: Replica<List<AppModel>>

    val appByIdReplica: KeyedReplica<PackageName, DetailedAppWrapper>
}