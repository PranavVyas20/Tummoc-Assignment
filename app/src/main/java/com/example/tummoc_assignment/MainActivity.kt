package com.example.tummoc_assignment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tummoc_assignment.screens.Screen1
import com.example.tummoc_assignment.screens.Screen2
import com.example.tummoc_assignment.ui.theme.TummocAssignmentTheme
import com.example.tummoc_assignment.ui_components.TestComponent
import com.example.tummoc_assignment.ui_components.screen_2.SelectedRouteItem
import com.example.tummoc_assignment.viewmodel.MainViewModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalMaterialApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TummocAssignmentTheme {


                val vm = hiltViewModel<MainViewModel>()
                val tempState = vm.shortestRouteState.value
                LaunchedEffect(key1 = Unit) {
                    vm.getShortestRoutes()
                }
                if (tempState.data != null) {
                    Screen2(list = tempState.data!![2].routes, vm)
                }


            }
        }
    }
}
