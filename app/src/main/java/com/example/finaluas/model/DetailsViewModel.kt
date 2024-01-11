package com.example.finaluas.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaluas.repository.RepositoryPesanan
import com.example.finaluas.ui.halamanPesanan.DetailsPesananDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryPesanan: RepositoryPesanan
) : ViewModel() {

    private val pesananId: Int = checkNotNull(savedStateHandle[DetailsPesananDestination.pesananIdArg])
    val uiState: StateFlow<ItemDetailUiState> =
        repositoryPesanan.getPesananStream(pesananId).filterNotNull()
            .map { ItemDetailUiState(detailPesanan = it.toDetailPesanan()) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailUiState()
            )

    suspend fun deleteItem() {
        repositoryPesanan.deletePesanan(uiState.value.detailPesanan.toPesanan())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailUiState(
    val outOfStock: Boolean = true,
    val detailPesanan: DetailPesanan = DetailPesanan()
)