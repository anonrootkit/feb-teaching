package com.example.feb.utils

import androidx.navigation.NavController

fun NavController.safeNavigate(actionId : Int) {
    currentDestination?.getAction(actionId)?.run { navigate(actionId) }
}