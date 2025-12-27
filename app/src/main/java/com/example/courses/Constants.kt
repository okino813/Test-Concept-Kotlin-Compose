package com.example.courses

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.courses.models.BottomNavItem
import com.example.courses.screens.Home
import com.example.courses.screens.Others
import com.example.courses.screens.Profile
import com.example.courses.screens.Settings

object Constants{
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home",
            composable = {}
        ),

        BottomNavItem(
            label = "Profile",
            icon = Icons.Filled.Person,
            route = "profile",
            composable = {Profile()}
        ),

        BottomNavItem(
            label = "Settings",
            icon = Icons.Filled.Settings,
            route = "settings",
            composable = {Settings()}
        ),

        BottomNavItem(
            label = "Others",
            icon = Icons.Filled.Info,
            route = "others",
            composable = {Others()}
        ),
    )
}