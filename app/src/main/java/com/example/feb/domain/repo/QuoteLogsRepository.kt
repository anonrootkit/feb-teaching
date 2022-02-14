package com.example.feb.domain.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.feb.data.db.AppDatabase
import com.example.feb.data.db.entities.QuoteLog
import com.example.feb.utils.LogsListMode
import com.example.feb.utils.PreferenceConstants
import com.example.feb.utils.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuoteLogsRepository(
    private val database: AppDatabase,
    private val dataStore: DataStore<Preferences>
) {

    private val logsListModePrefKey = stringPreferencesKey(PreferenceConstants.LOGS_LIST_MODE)

    companion object {
        private var instance : QuoteLogsRepository? = null

        fun getInstance(context: Context) : QuoteLogsRepository {
            return instance ?: synchronized(this) {
                instance = QuoteLogsRepository(
                    database = AppDatabase.getDatabase(context),
                    dataStore = context.dataStore
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

    suspend fun setLogsListMode(listMode : LogsListMode) {
        dataStore.edit {
            it[logsListModePrefKey] = listMode.name
        }
    }

    val logsListModeType: Flow<LogsListMode> = dataStore.data
        .map { preferences ->
            preferences[logsListModePrefKey]?.let { LogsListMode.valueOf(it) }
                ?: LogsListMode.PAGE
        }
}