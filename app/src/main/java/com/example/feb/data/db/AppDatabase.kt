package com.example.feb.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.feb.data.db.daos.QuoteLogsDao
import com.example.feb.data.db.entities.QuoteLog

@Database(entities = [QuoteLog::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quoteLogsDao() : QuoteLogsDao

    companion object {

        private const val DATABASE_NAME = "fab_db"

        private var database : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            return database ?: synchronized(this){
                database = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .build()

                database!!
            }
        }

    }

}

