package com.example.finaluas.repository

import com.example.finaluas.data.Sepeda
import kotlinx.coroutines.flow.Flow

interface RepositorySepeda {
    fun getAllSepedaStream(): Flow<List<Sepeda>>

    fun getSepedaStream(id : Int): Flow<Sepeda?>

    suspend fun insertSepeda(sepeda: Sepeda)

    suspend fun deleteSepeda(sepeda: Sepeda)

    suspend fun updateSepeda(sepeda: Sepeda)
}