package com.example.finaluas.repository

import com.example.finaluas.data.Pesanan
import kotlinx.coroutines.flow.Flow

interface RepositoryPesanan {
    fun getAllPesananStream(): Flow<List<Pesanan>>

    fun getPesananStream(id : Int): Flow<Pesanan?>

    suspend fun insertPesanan(pesanan: Pesanan)

    suspend fun deletePesanan(pesanan: Pesanan)

    suspend fun updatePesanan(pesanan: Pesanan)
}