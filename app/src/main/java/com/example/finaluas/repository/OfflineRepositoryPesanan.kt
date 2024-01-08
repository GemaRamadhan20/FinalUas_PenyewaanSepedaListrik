package com.example.finaluas.repository

import com.example.finaluas.data.Pesanan
import com.example.finaluas.data.PesananDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoryPesanan(private val pesananDao: PesananDao) : RepositoryPesanan {
    override fun getAllPesananStream(): Flow<List<Pesanan>> = pesananDao.getAllPesanan()

    override fun getPesananStream(id: Int): Flow<Pesanan?> =   pesananDao.getPesanan(id)

    override suspend fun insertPesanan(pesanan: Pesanan) = pesananDao.insert(pesanan)

    override suspend fun deletePesanan(pesanan: Pesanan) = pesananDao.delete(pesanan)

    override suspend fun updatePesanan(pesanan: Pesanan) = pesananDao.update(pesanan)

}