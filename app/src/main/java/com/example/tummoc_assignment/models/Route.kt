package com.example.tummoc_assignment.models

data class Route(
    val sourceTitle: String,
    val destinationTitle: String,
    val busRouteDetails: BusRouteDetail?,
    val medium: String,
    val duration: String,
    val fare: Double,
    val distance: Double,
    val routeName: String?,
    val trails: List<Trail>?
)
