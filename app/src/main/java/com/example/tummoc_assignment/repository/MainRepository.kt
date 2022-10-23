package com.example.tummoc_assignment.repository

import android.content.Context
import android.util.Log
import com.example.tummoc_assignment.models.routes.ShortestRoute
import com.example.tummoc_assignment.util.Constants
import com.example.tummoc_assignment.util.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class MainRepository(context: Context) {
    val ctx = context

    // Function to mimic api call
    suspend fun getShortestRoutes(context: Context): Flow<Resource<List<ShortestRoute>>> {
        return flow {
            emit(Resource(Constants.STATUS_LOADING))
            lateinit var jsonString: String
            try {
                jsonString = context.assets.open("routes_response.json").bufferedReader()
                    .use { it.readText() }
                val shortestRoutes = object : TypeToken<List<ShortestRoute>>() {}.type
                emit(
                    Resource(
                        status = Constants.STATUS_SUCCESS,
                        data = Gson().fromJson(jsonString, shortestRoutes)
                    )
                )
            } catch (ioException: IOException) {
                emit(Resource(Constants.STATUS_ERROR))
                Log.d("jsonTagg", "error parsing json")
            }
        }
    }
}