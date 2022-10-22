package com.example.tummoc_assignment.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tummoc_assignment.models.ShortestRoute
import com.example.tummoc_assignment.repository.MainRepository
import com.example.tummoc_assignment.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    data class UIState<T : Any>(
        val isLoading: Boolean = true,
        val data: T? = null,
        val error: String = ""
    )
    private val _shortestRoutesState = mutableStateOf<UIState<List<ShortestRoute>>>(UIState())
    val shortestRouteState get() = _shortestRoutesState

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
                        isLoading = false,
                        data = result.data
                    )
                }
                Constants.STATUS_ERROR -> {
                    _shortestRoutesState.value = UIState(
                        isLoading = false,
                        data = null,
                        error = Constants.ERROR_MESSAGE
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}