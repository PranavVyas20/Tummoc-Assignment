package com.example.tummoc_assignment.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tummoc_assignment.models.routes.Route
import com.example.tummoc_assignment.ui_components.screen_2.SelectedRouteItem
import com.example.tummoc_assignment.viewmodel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Screen2(navController: NavController, viewModel: MainViewModel) {

    if(viewModel.currentSelectedRoutes.isNotEmpty()) {
        val list = viewModel.currentSelectedRoutes

        val lastIdx = list.size - 1
        val sourceCoordinates =
            remember { mutableStateOf(LatLng(list[0].sourceLat, list[0].sourceLong)) }

        val destinationCoordinates = remember {
            mutableStateOf(
                LatLng(
                    list[lastIdx].destinationLat,
                    list[lastIdx].destinationLong
                )
            )
        }

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(sourceCoordinates.value, 11f)
        }
        var isMapLoaded = remember { mutableStateOf(false) }

        BottomSheetScaffold(
            sheetContent = {
                if (isMapLoaded.value) {
                    Box(Modifier.heightIn(min = 0.dp, max = 380.dp)) {
                        LazyColumn(Modifier.padding(15.dp)) {
                            items(list) { item ->
                                SelectedRouteItem(
                                    selectedRoute = item,
                                    onClick = {
                                        cameraPositionState.move(
                                            CameraUpdateFactory.newLatLng(
                                                LatLng(
                                                    item.sourceLat,
                                                    item.sourceLong
                                                )
                                            )
                                        )
                                        sourceCoordinates.value =
                                            LatLng(item.sourceLat, item.sourceLong)
                                        destinationCoordinates.value =
                                            LatLng(item.destinationLat, item.destinationLong)
                                    })
                            }
                        }
                    }
                }
                if (!isMapLoaded.value) {
                    CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
                }

            },
            sheetBackgroundColor = Color.White
        ) {
            GoogleMapView(
                cameraPositionState,
                isMapLoaded,
                sourceCoordinates.value,
                destinationCoordinates.value,
            )
        }
    }

}


@Composable
fun GoogleMapView(
    cameraPositionState: CameraPositionState,
    isMapLoaded: MutableState<Boolean>,
    sourceCoordinates: LatLng,
    destinationCoordinates: LatLng
) {
    GoogleMap(
        cameraPositionState = cameraPositionState,
        onMapLoaded = { isMapLoaded.value = true }
    ) {
        Marker(state = MarkerState(position = sourceCoordinates))
        Marker(state = MarkerState(position = destinationCoordinates))
        Polyline(points = listOf(sourceCoordinates, destinationCoordinates), color = Color.Blue)
    }
}

