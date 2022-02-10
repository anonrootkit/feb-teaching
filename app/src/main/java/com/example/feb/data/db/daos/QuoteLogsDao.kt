package com.example.feb.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feb.data.db.entities.QuoteLog

@Dao
interface QuoteLogsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuoteLog(log : QuoteLog)

    @Query("SELECT * FROM quote_logs ORDER BY time ASC")
    suspend fun getAllQuoteLogs() : List<QuoteLog>

    @Query("DELETE FROM quote_logs")
    suspend fun deleteQuoteLogs()
}