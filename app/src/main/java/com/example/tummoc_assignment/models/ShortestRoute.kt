package com.example.tummoc_assignment.models

data class ShortestRoute(
    val routes: List<Route>,
    val totalDuration: String,
    val totalFare: Double,
    val totalDistance: Double

)