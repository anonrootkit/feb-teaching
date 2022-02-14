package com.example.feb.domain.repo

import android.content.Context
import android.content.SharedPreferences
import com.example.feb.data.db.AppDatabase
import com.example.feb.data.db.entities.QuoteLog
import com.example.feb.data.preferences.PreferenceStorage
import com.example.feb.utils.LogsListMode
import com.example.feb.utils.PreferenceConstants

class QuoteLogsRepository(
    private val database: AppDatabase,
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private var instance : QuoteLogsRepository? = null

        fun getInstance(context: Context) : QuoteLogsRepository {
            return instance ?: synchronized(this) {
                instance = QuoteLogsRepository(
                    database = AppDatabase.getDatabase(context),
                    sharedPreferences = PreferenceStorage.getPreferences(context)
                )

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

    fun setLogsListMode(listMode : LogsListMode) {
        sharedPreferences.edit().putString(PreferenceConstants.LOGS_LIST_MODE, listMode.name).apply()
    }

    fun getLogsListMode() : LogsListMode {
        val mode = sharedPreferences.getString(PreferenceConstants.LOGS_LIST_MODE, null)
        return if (mode == null) LogsListMode.PAGE else LogsListMode.valueOf(mode)
    }
}