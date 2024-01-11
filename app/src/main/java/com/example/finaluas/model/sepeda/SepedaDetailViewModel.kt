package com.example.finaluas.model.sepeda

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finaluas.repository.RepositorySepeda
import com.example.finaluas.ui.halamanSepeda.DetailsSepedaDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SepedaDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySepeda: RepositorySepeda
) : ViewModel() {

    private val sepedaId: Int = checkNotNull(savedStateHandle[DetailsSepedaDestination.sepedaIdArg])
    val sepedauiState: StateFlow<SepedaDetailUiState> =
        repositorySepeda.getSepedaStream(sepedaId).filterNotNull()
            .map { SepedaDetailUiState(detailSepeda = it.toDetailSepeda()) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = SepedaDetailUiState()
            )

    suspend fun deleteSepeda() {
        repositorySepeda.deleteSepeda(sepedauiState.value.detailSepeda.toSepeda())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class SepedaDetailUiState(
    val outOfStock: Boolean = true,
    val detailSepeda: DetailSepeda = DetailSepeda()
)