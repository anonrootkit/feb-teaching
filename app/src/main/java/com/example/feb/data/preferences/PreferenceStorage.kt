package com.example.feb.data.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.feb.utils.PreferenceConstants

class PreferenceStorage {

    companion object{
        private var instance : SharedPreferences? = null

        fun getPreferences(context: Context) : SharedPreferences {
            return instance ?: synchronized(this) {
                instance = context.getSharedPreferences(PreferenceConstants.PREFERENCE_NAME, MODE_PRIVATE)
                instance!!
            }
        }
    }

}