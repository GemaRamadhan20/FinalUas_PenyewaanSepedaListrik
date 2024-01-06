package com.example.finaluas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pesanan::class], version = 1, exportSchema = false)
abstract class DatabaseAdmin : RoomDatabase(){
    abstract fun DataPesananDao() : DataPesananDao

    companion object {
        @Volatile
        private var Instance: DatabaseAdmin? = null

        fun getDatabase(context: Context): DatabaseAdmin {
            return Instance?: synchronized(this){
                Room.databaseBuilder(context,
                    DatabaseAdmin::class.java,
                    "sepedalistrik_database")
                    .build().also { Instance=it}
            }
        }
    }
}