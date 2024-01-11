package com.example.finaluas.model.sepeda

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.example.finaluas.data.Sepeda
import com.example.finaluas.repository.RepositorySepeda

class SepedaEntryViewModel(private val repositorySepeda: RepositorySepeda) : ViewModel() {
    var uiStateSepeda by mutableStateOf(UIStateSepeda())
        private set

    private fun validasiInput(uiState: DetailSepeda = uiStateSepeda.detailSepeda): Boolean {
        return with(uiState) {
            nama.isNotBlank() && merk.isNotBlank() && harga.isNotBlank()
        }
    }

    suspend fun saveSepeda() {
        if (validasiInput()) {
            repositorySepeda.insertSepeda(uiStateSepeda.detailSepeda.toSepeda())
        }
    }

    fun updateUiState(detailSepeda: DetailSepeda) {
        uiStateSepeda = UIStateSepeda(
            detailSepeda = detailSepeda,
            isEntryValid = validasiInput(detailSepeda)
        )
    }


}

data class UIStateSepeda(
    val detailSepeda: DetailSepeda = DetailSepeda(),
    val isEntryValid: Boolean = false
)


data class DetailSepeda(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String = "",
    val merk: String = "",
    val harga: String = ""
)

fun DetailSepeda.toSepeda(): Sepeda = Sepeda(
    id = id,
    nama = nama,
    merk = merk,
    harga = harga,
)

fun Sepeda.toUiStateSepeda(isEntryValid: Boolean = false): UIStateSepeda = UIStateSepeda(
    detailSepeda = this.toDetailSepeda(),
    isEntryValid = isEntryValid
)

fun Sepeda.toDetailSepeda(): DetailSepeda = DetailSepeda(
    id = id,
    nama = nama,
    merk = merk,
    harga = harga,
)