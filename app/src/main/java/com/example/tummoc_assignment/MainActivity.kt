package com.example.tummoc_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.tummoc_assignment.navigation.ScreenNavigation
import com.example.tummoc_assignment.ui.theme.TummocAssignmentTheme
import com.example.tummoc_assignment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TummocAssignmentTheme {
                val navController = rememberNavController()
                val vm = hiltViewModel<MainViewModel>()
                ScreenNavigation(navController = navController, viewModel = vm)
            }
        }
    }
}

