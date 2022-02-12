package com.example.feb.data.db.entities

import android.graphics.Color
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "quote_logs")
data class QuoteLog(
    @PrimaryKey
    @ColumnInfo(name = "time")
    val time : Long,

    @ColumnInfo(name = "quote")
    val quote : String,

    @ColumnInfo(name = "color")
    val color : String,
) : Parcelable {
    fun getFormattedDate() : String {
        return "12 Feb, 2022"
    }

    fun getParsedColor() : Int {
        return Color.parseColor(color)
    }
}
