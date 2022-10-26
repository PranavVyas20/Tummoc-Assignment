package com.example.tummoc_assignment.viewmodel

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tummoc_assignment.models.fastest_route.FastestRoute
import com.example.tummoc_assignment.models.fastest_route.MediumIconInfo
import com.example.tummoc_assignment.models.fastest_route.MediumIconWithDuration
import com.example.tummoc_assignment.models.routes.Route
import com.example.tummoc_assignment.models.routes.ShortestRoute
import com.example.tummoc_assignment.repository.MainRepository
import com.example.tummoc_assignment.ui.theme.busMediumColor
import com.example.tummoc_assignment.ui.theme.walkMediumColor
import com.example.tummoc_assignment.util.Constants
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    init {
        Log.d("initVm","vm inited")
    }
    data class UIState<T : Any>(
        val isLoading: Boolean = true, var data: T? = null, val error: String = ""
    )

    var currentSelectedRoutes = mutableListOf<Route>()

    private val _shortestRoutesState = mutableStateOf<UIState<List<ShortestRoute>>>(UIState())
    val shortestRouteState get() = _shortestRoutesState

    private val _fastestRoutesState = mutableStateOf<UIState<List<FastestRoute>>>(UIState())
    val fastestRouteState get() = _fastestRoutesState

    private val _totalJourneyCoordState = mutableStateOf<UIState<List<LatLng>>>(UIState())
    val totalJourneyCoordState get() = _totalJourneyCoordState

     fun getTotalJourneyCoords(routes: List<Route>) {
        val coordsList = mutableListOf<LatLng>()

        routes.forEach { eachRoute ->
            coordsList.add(LatLng(eachRoute.sourceLat, eachRoute.sourceLat))
            if (eachRoute.trails != null) {
                eachRoute.trails.forEach { eachTrail ->
                    coordsList.add(LatLng(eachTrail.latitude, eachTrail.longitude))
                }
            }
        }
         Log.d("checkTag", coordsList.toString())
        _totalJourneyCoordState.value = UIState(false, coordsList)
    }

    private fun formatDistance(distanceInKms: Double): String {
        // eg - 12.223km - 12 km
        if (distanceInKms >= 1) {
            return "${distanceInKms.toInt()} Km"
        }
        // eg - 0.12322 - 123 mtrs
        val inMtrs = (distanceInKms * 1000).toInt()
        return if (inMtrs == 0) {
            "1 Mtr"
        } else {
            "$inMtrs Mtr"
        }
    }

    private fun formatDuration(duration: String): String {
        val hr = duration.substring(0, 2)
        val mins = duration.substring(3, 5)

        // Check if 0 hrs
        val formattedDuration: String = if (hr != "00") {
            "$hr hrs $mins mins"
        } else if (mins == "00") {
            "01 mins"
        } else {
            "$mins mins"
        }
        return formattedDuration
    }

    private fun getDurationWeight(duration: String): Float {
        // Convert the time in mins and use that as line weight
        val hr = duration.substring(0, 2).toFloat()
        val mins = duration.substring(3, 5).toFloat()
        // If 0 mins and some secs
        if (duration.substring(3, 5) == "00") {
            return 5f
        }
        if (mins <= 5) {
            return 7f
        }
        return (hr * 60) + mins
    }

    private fun getMedianIconsWithWeight(routes: List<Route>): List<MediumIconWithDuration> {
        val medianIconsDuration = mutableListOf<MediumIconWithDuration>()
        routes.forEach { route ->
            val busIcon = Icons.Default.DirectionsBus
            val walkIcon = Icons.Default.DirectionsWalk
            val icon = if (route.medium == Constants.Median.WALK) walkIcon else busIcon
            val color =
                if (route.medium == Constants.Median.WALK) busMediumColor else walkMediumColor

            val duration = getDurationWeight(route.duration)
            medianIconsDuration.add(MediumIconWithDuration(icon, duration, color))
        }
        return medianIconsDuration
    }

    private fun getMedianIconsInfo(routes: List<Route>): List<MediumIconInfo> {
        val mediumIconInfo = mutableListOf<MediumIconInfo>()

        for (i in routes.indices) {
            // If last index then do not add the arrow icon
            when (routes[i].medium) {

                Constants.Median.WALK -> {
                    mediumIconInfo.add(
                        MediumIconInfo(
                            mediumIcon = Icons.Default.DirectionsWalk,
                            info = routes[i].duration,
                            arrowIcon = if (i != routes.indices.last) Icons.Default.ArrowRight else null
                        )
                    )
                }

                Constants.Median.BUS -> {
                    mediumIconInfo.add(
                        MediumIconInfo(
                            mediumIcon = Icons.Default.DirectionsBus,
                            info = routes[i].busRouteDetails!!.routeNumber,
                            arrowIcon = if (i != routes.indices.last) Icons.Default.ArrowRight else null
                        )
                    )
                }
            }
        }
        return mediumIconInfo
    }

    private fun getFastestRoutes(shortestRoutes: List<ShortestRoute>) {
        var idx = 0
        val fastestRoutes = mutableListOf<FastestRoute>()

        shortestRoutes.forEach {
            val fastestRoute = FastestRoute(
                mediumIconsDuration = getMedianIconsWithWeight(it.routes),
                mediumIconsInfo = getMedianIconsInfo(it.routes),
                duration = it.totalDuration,
                fare = it.totalFare,
                distance = it.totalDistance,
                index = idx
            )
            fastestRoutes.add(fastestRoute)
            idx++
        }
        _fastestRoutesState.value = UIState(isLoading = false, data = fastestRoutes)
    }

    suspend fun getShortestRoutes() {
        repository.getShortestRoutes(repository.ctx).onEach { result ->
            when (result.status) {
                Constants.STATUS_LOADING -> {
                    _shortestRoutesState.value = UIState(
                        isLoading = true
                    )
                }
                Constants.STATUS_SUCCESS -> {
                    _shortestRoutesState.value = UIState(
                        isLoading = false, data = result.data
                    )
                    result.data!!.forEach {
                        val routes = it.routes
                        routes.forEach { route ->
                            route.formattedDuration = formatDuration(route.duration)
                            route.formattedDistance = formatDistance(route.distance)
                        }
                    }
                    getFastestRoutes(result.data!!)
                }
                Constants.STATUS_ERROR -> {
                    _shortestRoutesState.value = UIState(
                        isLoading = false, data = null, error = Constants.ERROR_MESSAGE
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}