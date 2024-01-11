package com.example.finaluas.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finaluas.AplikasiSepeda
import com.example.finaluas.model.sepeda.SepedaDetailViewModel
import com.example.finaluas.model.sepeda.SepedaEditViewModel
import com.example.finaluas.model.sepeda.SepedaEntryViewModel
import com.example.finaluas.model.sepeda.SepedaHomeViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiSepeda().container.repositoryPesanan)
        }
        initializer {
            EntryViewModel(aplikasiSepeda().container.repositoryPesanan)
        }
        initializer {
            DetailsViewModel(createSavedStateHandle(), aplikasiSepeda().container.repositoryPesanan)
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiSepeda().container.repositoryPesanan,
            )
        }

        //

        initializer {
            SepedaHomeViewModel(aplikasiSepeda().container.repositorySepeda)
        }
        initializer {
            SepedaEntryViewModel(aplikasiSepeda().container.repositorySepeda)
        }
        initializer {
            SepedaDetailViewModel(createSavedStateHandle(),aplikasiSepeda().container.repositorySepeda)
        }
        initializer {
            SepedaEditViewModel(createSavedStateHandle(),aplikasiSepeda().container.repositorySepeda)
        }
    }
}

fun CreationExtras.aplikasiSepeda(): AplikasiSepeda =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSepeda)