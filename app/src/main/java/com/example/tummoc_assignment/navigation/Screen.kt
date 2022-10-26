package com.example.tummoc_assignment.navigation

sealed class Screen(val route: String) {
    object Screen1 : Screen("screen_1")
    object Screen2 : Screen("screen_2")
}
