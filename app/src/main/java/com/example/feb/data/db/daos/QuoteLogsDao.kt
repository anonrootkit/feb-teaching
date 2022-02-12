package com.example.feb.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.feb.data.db.entities.QuoteLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface QuoteLogsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuoteLog(log : QuoteLog)

    @Query("SELECT * FROM quote_logs ORDER BY time ASC")
    fun getAllQuoteLogs() : Flow<List<QuoteLog>>

    @Update
    suspend fun updateQuoteLog(quoteLog: QuoteLog)

    @Query("DELETE FROM quote_logs")
    suspend fun deleteQuoteLogs()
}