package com.example.finaluas.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaluas.data.Pesanan
import com.example.finaluas.repository.OfflineRepositoryPesanan
import com.example.finaluas.repository.RepositoryPesanan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val repositoryPesanan: RepositoryPesanan) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoryPesanan.getAllPesananStream().filterNotNull()
        .map { HomeUiState(listPesanan = it.toList()) }.stateIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(
                TIMEOUT_MILLIS
            ), initialValue = HomeUiState()
        )

    data class HomeUiState(
        val listPesanan: List<Pesanan> = listOf()
    )
}
