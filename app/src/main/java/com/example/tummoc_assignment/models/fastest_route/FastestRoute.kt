package com.example.tummoc_assignment.models.fastest_route

data class FastestRoute(
    val mediumIconsDuration: List<MediumIconWithDuration>,
    val mediumIconsInfo: List<MediumIconInfo>,
    val duration: String,
    val fare: Double,
    val distance: Double,
    val index: Int
)
