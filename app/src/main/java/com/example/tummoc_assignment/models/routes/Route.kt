package com.example.tummoc_assignment.models.routes

data class Route(
    val sourceTitle: String,
    val sourceLong: Double,
    val sourceLat: Double,
    val destinationTitle: String,
    val destinationLat: Double,
    val destinationLong: Double,
    val isFinalRoute: Boolean?,
    val busRouteDetails: BusRouteDetail?,
    val medium: String,
    val duration: String,
    var formattedDuration: String,
    var formattedDistance: String,
    val fare: Double,
    val distance: Double,
    val routeName: String?,
    val trails: List<Trail>?
)
