package com.example.tummoc_assignment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tummoc_assignment.screens.Screen1
import com.example.tummoc_assignment.screens.Screen2
import com.example.tummoc_assignment.viewmodel.MainViewModel

@Composable
fun ScreenNavigation(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Screen1.route) {
        composable(
            route = Screen.Screen1.route
        ) {
            Screen1(viewModel = viewModel, navController = navController)
        }

        composable(
            route = Screen.Screen2.route
        ) {
            Screen2(viewModel = viewModel)
        }
    }
}