package com.example.tummoc_assignment.models.routes

data class ShortestRoute(
    val routes: List<Route>,
    val totalDuration: String,
    val totalFare: Double,
    val totalDistance: Double
)