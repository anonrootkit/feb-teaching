package com.example.feb.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.feb.data.db.entities.QuoteLog

@Dao
interface QuoteLogsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuoteLog(log : QuoteLog)

    @Query("SELECT * FROM quote_logs ORDER BY time ASC")
    fun getAllQuoteLogs() : LiveData<List<QuoteLog>>

    @Update
    suspend fun updateQuoteLog(quoteLog: QuoteLog)

    @Query("DELETE FROM quote_logs")
    suspend fun deleteQuoteLogs()
}