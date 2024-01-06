package com.example.finaluas.repository

import com.example.finaluas.data.DataPesananDao
import com.example.finaluas.data.Pesanan
import kotlinx.coroutines.flow.Flow

class OfflineRepositoryPesanan (private val dataPesananDao: DataPesananDao) : RepositoryPesanan {
    override fun getAllPesananStream(): Flow<List<Pesanan>> {
        return dataPesananDao.getAllPesanan()
    }

    override fun getPesananStream(id: Int): Flow<Pesanan?> {
        return dataPesananDao.getPesanan(id = id)
    }

    override suspend fun insertPesanan(pesanan: Pesanan) = dataPesananDao.insert(pesanan)

    override suspend fun updatePesanan(pesanan: Pesanan) {
        return dataPesananDao.update(pesanan)
    }

}