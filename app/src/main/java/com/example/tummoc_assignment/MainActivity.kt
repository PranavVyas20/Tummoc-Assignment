package com.example.tummoc_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.tummoc_assignment.ui.theme.TummocAssignmentTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TummocAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    test()
                }
            }
        }
    }
}

@Composable
fun test() {
    Column() {
        val l1 = LatLng(-34.3, 151.1)
        val l2 = LatLng(35.66, 139.6)

        val jaipurCoord = LatLng(-34.3, 151.1)
        GoogleMap(
            modifier = Modifier.weight(1f),
            cameraPositionState = CameraPositionState(
                CameraPosition(
                    jaipurCoord, 12f, 0f,0f
                )
            )
        ) {
            Polyline(points = listOf(l1,l2))
//            Marker(
//                state = MarkerState(position = LatLng(-34.3, 151.1)),
//                title = "Marker in Sydney"
//            )
//            Marker(
//                state = MarkerState(position = LatLng(35.66, 139.6)),
//                title = "Marker in Tokyo"
//            )
        }
        Card(modifier = Modifier
            .fillMaxSize()
            .weight(1f), backgroundColor = Color.Red) {

        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TummocAssignmentTheme {

    }
}