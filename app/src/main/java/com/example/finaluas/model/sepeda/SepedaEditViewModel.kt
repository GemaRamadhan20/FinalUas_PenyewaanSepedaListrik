package com.example.finaluas.model.sepeda

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaluas.repository.RepositorySepeda
import com.example.finaluas.ui.halamanSepeda.SepedaEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SepedaEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySepeda: RepositorySepeda
) : ViewModel() {

    var sepedaUiState by mutableStateOf(UIStateSepeda())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[SepedaEditDestination.sepedaIdArg])

    init {
        viewModelScope.launch {
            sepedaUiState = repositorySepeda.getSepedaStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateSepeda(true)
        }
    }
    suspend fun updateSepeda() {
        if (validasiInput(sepedaUiState.detailSepeda)) {
            repositorySepeda.updateSepeda(sepedaUiState.detailSepeda.toSepeda())
        }
        else {
            println("Data tidak valid")
        }
    }
    fun updateUiState(detailSepeda: DetailSepeda) {
        sepedaUiState =
            UIStateSepeda(detailSepeda = detailSepeda, isEntryValid = validasiInput(detailSepeda))
    }
    private fun validasiInput(uiState: DetailSepeda = sepedaUiState.detailSepeda ): Boolean {
        return with(uiState) {
            nama.isNotBlank() && merk.isNotBlank() && harga.isNotBlank()}
    }


}