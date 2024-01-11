package com.example.finaluas.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaluas.repository.RepositoryPesanan
import com.example.finaluas.ui.halamanPesanan.PesananEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryPesanan: RepositoryPesanan
) : ViewModel() {

    var pesananUiState by mutableStateOf(UIStatePesanan())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[PesananEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            pesananUiState = repositoryPesanan.getPesananStream(itemId)
                .filterNotNull()
                .first()
                .toUiStatePesanan(true)
        }
    }
    suspend fun updatePesanan() {
        if (validasiInput(pesananUiState.detailPesanan)) {
            repositoryPesanan.updatePesanan(pesananUiState.detailPesanan.toPesanan())
        }
        else {
            println("Data tidak valid")
        }
    }
    fun updateUiState(detailPesanan: DetailPesanan) {
        pesananUiState =
            UIStatePesanan(detailPesanan = detailPesanan, isEntryValid = validasiInput(detailPesanan))
    }
    private fun validasiInput(uiState: DetailPesanan = pesananUiState.detailPesanan ): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank() && jaminan.isNotBlank()
        }
    }


}