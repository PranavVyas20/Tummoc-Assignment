package com.example.tummoc_assignment.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tummoc_assignment.models.fastest_route.FastestRoute
import com.example.tummoc_assignment.models.fastest_route.MediumIconInfo
import com.example.tummoc_assignment.models.routes.Route
import com.example.tummoc_assignment.models.routes.ShortestRoute
import com.example.tummoc_assignment.repository.MainRepository
import com.example.tummoc_assignment.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    data class UIState<T : Any>(
        val isLoading: Boolean = true,
        var data: T? = null,
        val error: String = ""
    )

    private val _shortestRoutesState = mutableStateOf<UIState<List<ShortestRoute>>>(UIState())
    val shortestRouteState get() = _shortestRoutesState

    private val _fastestRoutesState = mutableStateOf<UIState<List<FastestRoute>>>(UIState())
    val fastestRouteState get() = _fastestRoutesState

    private fun getMedianIcons(routes: List<Route>): List<ImageVector> {
        val medianIcons = mutableListOf<ImageVector>()
        routes.forEach { route ->
            val icon =
                if (route.medium == Constants.Median.WALK) Icons.Default.DirectionsWalk else Icons.Default.DirectionsBus
            medianIcons.add(icon)
        }
        return medianIcons
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
        val fastestRoutes = mutableListOf<FastestRoute>()

        shortestRoutes.forEach {
            val fastestRoute = FastestRoute(
                mediumIcons = getMedianIcons(it.routes),
                mediumIconsInfo = getMedianIconsInfo(it.routes),
                duration = it.totalDuration,
                fare = it.totalFare,
                distance = it.totalDistance
            )
            fastestRoutes.add(fastestRoute)
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