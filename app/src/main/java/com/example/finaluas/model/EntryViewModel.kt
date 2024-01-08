package com.example.finaluas.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.finaluas.data.Pesanan
import com.example.finaluas.repository.RepositoryPesanan

class EntryViewModel(private val repositoryPesanan: RepositoryPesanan): ViewModel() {
    var uiStatePesanan by mutableStateOf(UIStatePesanan())
        private set

    private fun validasiInput(uiState: DetailPesanan = uiStatePesanan.detailPesanan): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() &&nohp.isNotBlank() &&jaminan.isNotBlank()
        }
    }
    fun updateUiState(detailPesanan: DetailPesanan){
        uiStatePesanan = UIStatePesanan(detailPesanan = detailPesanan, isEntryValid = validasiInput(detailPesanan))
    }



}
data class UIStatePesanan(
    val detailPesanan: DetailPesanan = DetailPesanan(),
    val isEntryValid: Boolean = false
)


data class DetailPesanan(
    val id : Int = 0,
    val nama : String = "",
    val nohp : String = "",
    val alamat : String="",
    val jaminan : String=""
)
fun DetailPesanan.toPesanan(): Pesanan = Pesanan(
    id = id,
    nama = nama,
    nohp = nohp,
    alamat = alamat,
    jaminan = jaminan
)
fun Pesanan.toUiStatePesanan(isEntryValid: Boolean = false): UIStatePesanan = UIStatePesanan(
    detailPesanan = this.toDetailPesanan(),
    isEntryValid = isEntryValid
)
fun Pesanan.toDetailPesanan(): DetailPesanan = DetailPesanan(
    id = id,
    nama = nama,
    nohp = nohp,
    alamat = alamat,
    jaminan = jaminan
)