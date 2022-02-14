package com.example.feb.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController

fun NavController.safeNavigate(actionId : Int) {
    currentDestination?.getAction(actionId)?.run { navigate(actionId) }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = PreferenceConstants.PREFERENCE_NAME,
    produceMigrations = {
        // Since we're migrating from SharedPreferences, add a migration based on the
        // SharedPreferences name
        listOf(SharedPreferencesMigration(it, PreferenceConstants.PREFERENCE_NAME))
    }
)