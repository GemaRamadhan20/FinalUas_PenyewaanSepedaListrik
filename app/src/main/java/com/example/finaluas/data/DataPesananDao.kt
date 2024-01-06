package com.example.finaluas.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DataPesananDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pesanan: Pesanan)

    @Update
    suspend fun update(pesanan: Pesanan)

    @Delete
    suspend fun delete(pesanan: Pesanan)

    @Query("SELECT * from tbl_pesanan WHERE id = id")
    fun getPesanan(id: Int): Flow<Pesanan>

    @Query("SELECT * from tbl_pesanan ORDER BY nama ASC")
    fun getAllPesanan(): Flow<List<Pesanan>>
}