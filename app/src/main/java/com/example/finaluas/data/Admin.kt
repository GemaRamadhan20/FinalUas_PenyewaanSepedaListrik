package com.example.finaluas.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_admin")
data class Admin(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val username : String,
    val pass : String
)