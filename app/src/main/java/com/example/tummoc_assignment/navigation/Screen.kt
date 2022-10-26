package com.example.tummoc_assignment.navigation

sealed class Screen(val route: String) {
    object FastestRouteScreen : Screen("fastest_route_screen")
    object SelectedRouteScreen : Screen("selected_route_screen")
}
