package com.example.finaluas.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finaluas.AplikasiSepeda

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(AplikasiSepeda().container.repositoryPesanan)
        }
        initializer {
            EntryViewModel(AplikasiSepeda().container.repositoryPesanan)
        }
        initializer {
            DetailsViewModel(createSavedStateHandle(), AplikasiSepeda().container.repositoryPesanan)
        }
        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                AplikasiSepeda().container.repositoryPesanan,
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                AplikasiSepeda().container.repositoryPesanan,
            )
        }
    }
}

fun CreationExtras.AplikasiSepeda(): AplikasiSepeda =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSepeda)