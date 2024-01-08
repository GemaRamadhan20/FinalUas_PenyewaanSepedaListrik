package com.example.finaluas

import android.app.Application
import com.example.finaluas.repository.ContainerApp
import com.example.finaluas.repository.ContainerDataApp

class AplikasiSepeda : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}