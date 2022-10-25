package com.example.tummoc_assignment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tummoc_assignment.screens.Screen1
import com.example.tummoc_assignment.ui.theme.TummocAssignmentTheme
import com.example.tummoc_assignment.viewmodel.MainViewModel
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
                /*                val state = vm.shortestRouteState.value
                val fastestRouteState = vm.fastestRouteState.value

                LaunchedEffect(key1 = Unit) {
                    vm.getShortestRoutes()
                }
                Log.d("jsonTagg",state.toString())
                Log.d("jsonTagg",fastestRouteState.toString())*/
                Screen1(viewModel = vm)

                /*                // A surface container using the 'background' color from the theme
                BottomSheetScaffold(
                    sheetContent = {
                        Box(Modifier.height(300.dp)){
                            LazyColumn(modifier = Modifier.fillMaxWidth()){
                                items(70, itemContent = {
                                    Text(text = "Bottom sheet item")
                                })
                            }
                        }
                    },
                    sheetBackgroundColor = Color.Red
                ) {
                    mapTestLayout()
                }*/
            }
        }
    }
}

@Composable
fun mapTestLayout() {
    Column() {
        val jaipurCoord = LatLng(26.9124, 75.7873)
        GoogleMap(
            modifier = Modifier.weight(1f),
            cameraPositionState = CameraPositionState(
                CameraPosition(
                    jaipurCoord, 12f, 0f, 0f
                )
            )
        ) {
        }
    }
}
