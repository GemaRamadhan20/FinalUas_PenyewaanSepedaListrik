package com.example.finaluas.repository

import android.content.Context
import com.example.finaluas.data.DatabasePesanan

interface ContainerApp {
    val repositoryPesanan : RepositoryPesanan
}

class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoryPesanan: RepositoryPesanan by lazy {
        OfflineRepositoryPesanan(DatabasePesanan.getDatabase(context).pesananDao())
    }
}