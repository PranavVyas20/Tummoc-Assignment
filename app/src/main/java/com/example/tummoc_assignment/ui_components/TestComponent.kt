package com.example.tummoc_assignment.ui_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
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
*/
@Composable
fun TestComponent() {
    val list1 = listOf(1, 2, 3, 4, 5)
    val list2 = listOf('a', 'b', 'c', 'd', 'e', 'd', 'f')
    nestedLazysTest(list1 = list1, list2 = list2)

}

@Composable
fun nestedLazysTest(list1: List<Int>, list2: List<Char>) {
    LazyColumn {
        items(list1) { item ->
            lazyItem(str = item.toString(), list2)
        }
    }
}

@Composable
fun lazyItem(str: String, mList2: List<Char>) {
    val expandedState = remember { mutableStateOf(false) }
    Card(
        backgroundColor = Color.Red, modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = str, modifier = Modifier.padding(20.dp))
            Button(onClick = { expandedState.value = !expandedState.value }) {
                Text(text = "Click to expand")
            }
            AnimatedVisibility(visible = expandedState.value) {
                Column(
                    Modifier
                        .height(90.dp)
                        .verticalScroll(rememberScrollState())) {
                    mList2.forEach {
                        Text(text = it.toString())
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun lazyPreview() {
    TestComponent()
}