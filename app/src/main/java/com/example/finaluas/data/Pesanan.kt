package com.example.finaluas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_pesanan")
data class  Pesanan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = "",
    val jaminan: String = "",
)