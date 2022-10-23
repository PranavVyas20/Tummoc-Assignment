package com.example.tummoc_assignment.models.fastest_route

import androidx.compose.ui.graphics.vector.ImageVector

data class FastestRoute(
    val mediumIcons: List<ImageVector>,
    val mediumIconsInfo: List<MediumIconInfo>,
    val duration: String,
    val fare: Double,
    val distance: Double
)
