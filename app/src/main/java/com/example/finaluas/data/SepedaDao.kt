package com.example.finaluas.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface SepedaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sepeda: Sepeda)

    @Update
    suspend fun update(sepeda: Sepeda)

    @Delete
    suspend fun delete(sepeda: Sepeda)

    @Query("SELECT * from tblSepeda WHERE id = :id")
    fun getSepeda(id: Int): Flow<Sepeda>

    @Query("SELECT * from tblSepeda ORDER BY nama ASC")
    fun getAllSepeda(): Flow<List<Sepeda>>
}