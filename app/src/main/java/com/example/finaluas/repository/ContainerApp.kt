package com.example.finaluas.repository

import android.content.Context
import com.example.finaluas.data.DatabasePesanan

interface ContainerApp {
    val repositoryPesanan: RepositoryPesanan
    val repositorySepeda: RepositorySepeda
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    override val repositoryPesanan: RepositoryPesanan by lazy {
        OfflineRepositoryPesanan(DatabasePesanan.getDatabase(context).pesananDao())
    }

    override val repositorySepeda: RepositorySepeda by lazy {
        OfflineRepositorySepeda(
            DatabasePesanan.getDatabase(context).sepedaDao()
        )
    }

}