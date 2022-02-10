package com.example.feb.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_logs")
data class QuoteLog(
    @PrimaryKey
    @ColumnInfo(name = "time")
    val time : Long,

    @ColumnInfo(name = "quote")
    val quote : String,

    @ColumnInfo(name = "color")
    val color : String,
)