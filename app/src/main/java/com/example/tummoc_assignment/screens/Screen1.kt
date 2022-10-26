package com.example.tummoc_assignment.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tummoc_assignment.models.fastest_route.FastestRoute
import com.example.tummoc_assignment.models.routes.Route
import com.example.tummoc_assignment.models.routes.ShortestRoute
import com.example.tummoc_assignment.navigation.Screen
import com.example.tummoc_assignment.ui.theme.buttonOrangeColor
import com.example.tummoc_assignment.ui_components.screen_1.DestinationSearchLayout
import com.example.tummoc_assignment.ui_components.screen_1.FastestRouteItem
import com.example.tummoc_assignment.ui_components.screen_1.TravellingMediumLayout
import com.example.tummoc_assignment.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Screen1(viewModel: MainViewModel, navController: NavHostController) {
    val fastestRouteState = viewModel.fastestRouteState.value
    val shortestRouteState = viewModel.shortestRouteState.value

    LaunchedEffect(key1 = Unit) {
        viewModel.getShortestRoutes()
    }

    Scaffold(backgroundColor = Color(0xFFF2F7F6), floatingActionButton = {
        FloatingActionButton()
    }) {
        val i = it
        Column {
            DestinationSearchLayout()
            TravellingMediumLayout()
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "FASTEST ROUTE",
                fontWeight = FontWeight.Bold
            )
            if (fastestRouteState.isLoading) {
                CircularProgressIndicator(
                    Modifier
                        .padding(top = 30.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            if (fastestRouteState.data != null) {
                FastestRouteLayout(
                    fastestRoutes = fastestRouteState.data!!,
                    shortestRoutes = shortestRouteState.data!!,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }

    }
}

@Composable
fun FloatingActionButton() {
    IconButton(modifier = Modifier
        .clip(CircleShape)
        .background(buttonOrangeColor), onClick = { }) {
        Icon(
            imageVector = Icons.Default.FilterAlt,
            contentDescription = "",
            tint = Color.White
        )
    }
}

@Composable
fun FastestRouteLayout(
    fastestRoutes: List<FastestRoute>,
    shortestRoutes: List<ShortestRoute>,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    LazyColumn(
        Modifier
            .wrapContentHeight()
            .padding(15.dp)
    ) {
        itemsIndexed(items = fastestRoutes, itemContent = { idx, item ->
            FastestRouteItem(
                fastestRoute = item,
                onClick = {
                    viewModel.currentSelectedRoutes = shortestRoutes[idx].routes as MutableList<Route>
                    navController.navigate(Screen.Screen2.route)
                }
            )
        })
    }
}

private fun goToNextScreen(shortestRoute: ShortestRoute) {
    Log.d("funCheck", shortestRoute.toString())
}