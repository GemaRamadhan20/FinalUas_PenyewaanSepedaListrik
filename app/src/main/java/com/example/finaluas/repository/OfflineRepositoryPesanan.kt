package com.example.finaluas.repository

import com.example.finaluas.data.PesananDao
import com.example.finaluas.data.Pesanan
import kotlinx.coroutines.flow.Flow

class OfflineRepositoryPesanan (private val PesananDao: PesananDao) : RepositoryPesanan {
    override fun getAllPesananStream(): Flow<List<Pesanan>> {
        return PesananDao.getAllPesanan()
    }

    override fun getPesananStream(id: Int): Flow<Pesanan?> {
        return PesananDao.getPesanan(id = id)
    }

    override suspend fun insertPesanan(pesanan: Pesanan) = PesananDao.insert(pesanan)

    override suspend fun updatePesanan(pesanan: Pesanan) {
        return PesananDao.update(pesanan)
    }

    override suspend fun deletePesanan(pesanan: Pesanan) {
        return PesananDao.delete(pesanan)
    }

}