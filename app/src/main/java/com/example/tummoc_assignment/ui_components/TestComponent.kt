package com.example.tummoc_assignment.ui_components

import android.graphics.Paint.Align
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tummoc_assignment.models.fastest_route.MediumIconWithDuration

@Preview
@Composable
fun TestComponent() {

//    val list1 = listOf<MediumIconWithDuration>(
//        MediumIconWithDuration()
//    )
   /* Row(
        Modifier
            .padding(30.dp)
            .fillMaxWidth()
    ) {
        Column(Modifier.weight(5f)) {
            Row(
                modifier = Modifier
                    .height(15.dp)
                    .fillMaxWidth()

                    .background(Color.Blue)

            ) {}
            Icon(imageVector = Icons.Default.DirectionsWalk, contentDescription = "", Modifier.align(Alignment.CenterHorizontally))
        }
        Column(Modifier.weight(5f)) {
            Row(
                modifier = Modifier
                    .height(15.dp)
                    .fillMaxWidth()

                    .background(Color.Red)
            ) {}
            Row() {
                Icon(imageVector = Icons.Default.DirectionsWalk, contentDescription = "", )
            }
        }
        Column(Modifier.weight(96f)) {
            Row(
                modifier = Modifier
                    .height(15.dp)
                    .fillMaxWidth()

                    .background(Color.White)
            ) {}
//            Row(Modifier.height(30.dp).width(30.dp).background(Color.Green), horizontalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.DirectionsWalk, contentDescription = "", )
//            }
        }
        Column(Modifier.weight(5f)) {
            Row(
                modifier = Modifier
                    .height(15.dp)
                    .fillMaxWidth()

                    .background(Color.Yellow)
            ) {}
            Icon(imageVector = Icons.Default.DirectionsWalk, contentDescription = "", )
        }
    }*/

    Column() {
        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier
                .height(15.dp)
                .weight(1f)
                .background(Color.Blue))
            Spacer(modifier = Modifier
                .height(15.dp)
                .weight(2f)
                .background(Color.Red))
            Spacer(modifier = Modifier
                .height(15.dp)
                .weight(46f)
                .background(Color.Green))
            Spacer(modifier = Modifier
                .height(15.dp)
                .weight(1f)
                .background(Color.White))
        }
        Row() {
            Row(Modifier.weight(1f)) {
                Icon(imageVector = Icons.Default.DirectionsWalk, contentDescription = "")
            }
            Row(Modifier.weight(6f)) {
                Icon(imageVector = Icons.Default.DirectionsWalk, contentDescription = "")
            }
            Row(Modifier.weight(5f)) {
                Icon(imageVector = Icons.Default.DirectionsWalk, contentDescription = "")
            }
            Row(Modifier.weight(1f)) {
                Icon(imageVector = Icons.Default.DirectionsWalk, contentDescription = "")
            }
        }
    }

}