package com.example.courses

sealed  class Routes(
    val route: String
) {
    data object Home : Routes("home")
    data object Profile : Routes("profile")
    data object Settings : Routes("setting")
}