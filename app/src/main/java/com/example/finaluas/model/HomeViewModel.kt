package com.example.finaluas.model

import androidx.lifecycle.ViewModel
import com.example.finaluas.repository.OfflineRepositoryPesanan

class HomeViewModel(private val repositoryPesanan: OfflineRepositoryPesanan): ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}