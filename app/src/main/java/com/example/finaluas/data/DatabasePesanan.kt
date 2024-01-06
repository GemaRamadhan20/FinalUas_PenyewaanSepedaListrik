package com.example.finaluas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pesanan::class], version = 1, exportSchema = false)
abstract class DatabasePesanan : RoomDatabase(){
    abstract fun DataPesananDao() : DataPesananDao

    companion object {
        @Volatile
        private var Instance: DatabasePesanan? = null

        fun getDatabase(context: Context): DatabasePesanan {
            return Instance?: synchronized(this){
                Room.databaseBuilder(context,
                    DatabasePesanan::class.java,
                    "sepedalistrik_database")
                    .build().also { Instance=it}
            }
        }
    }
}