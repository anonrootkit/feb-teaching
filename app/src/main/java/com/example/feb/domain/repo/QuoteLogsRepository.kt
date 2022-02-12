package com.example.feb.domain.repo

import android.content.Context
import com.example.feb.data.db.AppDatabase
import com.example.feb.data.db.entities.QuoteLog

class QuoteLogsRepository(private val database: AppDatabase) {

    companion object {
        private var instance : QuoteLogsRepository? = null

        fun getInstance(context: Context) : QuoteLogsRepository {
            return instance ?: synchronized(this) {
                instance = QuoteLogsRepository(database = AppDatabase.getDatabase(context))

                instance!!
            }
        }
    }

    fun getLogs() = database.quoteLogsDao().getAllQuoteLogs()

    suspend fun insertLog(log : QuoteLog) {
        database.quoteLogsDao().insertQuoteLog(log)
    }

    suspend fun updateLog(log : QuoteLog) {
        database.quoteLogsDao().updateQuoteLog(log)
    }

    suspend fun deleteLogs() = database.quoteLogsDao().deleteQuoteLogs()
}