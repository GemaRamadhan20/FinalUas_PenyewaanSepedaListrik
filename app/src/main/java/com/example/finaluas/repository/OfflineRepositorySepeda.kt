package com.example.finaluas.repository

import com.example.finaluas.data.Sepeda
import com.example.finaluas.data.SepedaDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositorySepeda(private val sepedaDao: SepedaDao) : RepositorySepeda {
    override fun getAllSepedaStream(): Flow<List<Sepeda>> = sepedaDao.getAllSepeda()

    override fun getSepedaStream(id: Int): Flow<Sepeda?> =   sepedaDao.getSepeda(id)

    override suspend fun insertSepeda(sepeda: Sepeda) = sepedaDao.insert(sepeda)

    override suspend fun deleteSepeda(sepeda: Sepeda) = sepedaDao.delete(sepeda)

    override suspend fun updateSepeda(sepeda: Sepeda) = sepedaDao.update(sepeda)

}