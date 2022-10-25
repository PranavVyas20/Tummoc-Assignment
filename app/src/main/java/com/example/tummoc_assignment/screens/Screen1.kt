package com.example.tummoc_assignment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tummoc_assignment.models.fastest_route.FastestRoute
import com.example.tummoc_assignment.ui.theme.buttonOrangeColor
import com.example.tummoc_assignment.ui_components.screen_1.DestinationSearchLayout
import com.example.tummoc_assignment.ui_components.screen_1.FastestRouteItem
import com.example.tummoc_assignment.ui_components.screen_1.TravellingMediumLayout
import com.example.tummoc_assignment.viewmodel.MainViewModel


@Composable
fun Screen1(viewModel: MainViewModel) {
    val fastestRouteState = viewModel.fastestRouteState.value

    LaunchedEffect(key1 = Unit) {
        viewModel.getShortestRoutes()
    }

    Scaffold(backgroundColor = Color(0xFFF2F7F6), floatingActionButton = {
        FloatingActionButton()
    }) {
        val i = it
        Column() {
            DestinationSearchLayout()
            TravellingMediumLayout()
            FastestRouteLayout(fastestRoutes = fastestRouteState.data ?: listOf())
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
fun FastestRouteLayout(fastestRoutes: List<FastestRoute>) {
    LazyColumn(
        Modifier
            .wrapContentHeight()
            .padding(15.dp)
    ) {
        items(fastestRoutes, itemContent = { item ->
            FastestRouteItem(item)
        })
    }
}