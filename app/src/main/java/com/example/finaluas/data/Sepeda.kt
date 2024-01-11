package com.example.finaluas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblSepeda")
data class Sepeda(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String = "",
    val merk: String = "",
    val harga: String = ""
)
