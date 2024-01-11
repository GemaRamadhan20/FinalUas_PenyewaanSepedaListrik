package com.example.finaluas.model.sepeda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaluas.data.Pesanan
import com.example.finaluas.data.Sepeda
import com.example.finaluas.repository.RepositoryPesanan
import com.example.finaluas.repository.RepositorySepeda
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SepedaHomeViewModel(private val repositorySepeda: RepositorySepeda) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<SepedaHomeUiState> = repositorySepeda.getAllSepedaStream().filterNotNull()
        .map { SepedaHomeUiState(listSepeda = it.toList()) }.stateIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(
                TIMEOUT_MILLIS
            ), initialValue = SepedaHomeUiState()
        )


}

data class SepedaHomeUiState(
    val listSepeda: List<Sepeda> = listOf()
)