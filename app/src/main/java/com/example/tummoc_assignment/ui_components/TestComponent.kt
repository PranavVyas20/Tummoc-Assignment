package com.example.tummoc_assignment.ui_components

import android.graphics.Paint.Align
import android.graphics.drawable.Icon
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.tummoc_assignment.R
import com.example.tummoc_assignment.models.fastest_route.MediumIconWithDuration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//@Preview
//@Composable
//fun TestComponent() {
//    val transition = animateFlo(targetValue = Color.Blue, animationSpec = repeatable(
//        1, animation = tween(800,100, FastOutLinearInEasing),
//        repeatMode = RepeatMode.Reverse
//    ))
//    Box(
//        modifier = Modifier
//            .background(Color.Red)
//            .height(30.dp)
//            .width(30.dp)
//    )
//}

@Preview
@Composable
private fun AnimateDpAsState() {
    val isNeedExpansion = remember{ mutableStateOf(false) }

    val animatedSizeDp: Float by animateFloatAsState(targetValue = if (isNeedExpansion.value) 0.5f else 1f )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircleImage(animatedSizeDp)
        Button(
            onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    isNeedExpansion.value = !isNeedExpansion.value
                    delay(300)
                    isNeedExpansion.value = !isNeedExpansion.value
                }
            },
            modifier = Modifier
                .padding(top = 50.dp)
                .width(300.dp)
        ) {
            Text(text = "animateDpAsState")
        }
    }
}

@Composable
fun CircleImage(size: Float) {
    Card(modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth()
        .scale(size),
    backgroundColor = Color.Red){
        Spacer(modifier = Modifier.height(50.dp))
    }
}

