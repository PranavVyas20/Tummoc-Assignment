package com.example.tummoc_assignment.ui_components.screen_1

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tummoc_assignment.models.fastest_route.FastestRoute
import com.example.tummoc_assignment.models.fastest_route.MediumIconInfo
import com.example.tummoc_assignment.models.fastest_route.MediumIconWithDuration
import com.example.tummoc_assignment.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FastestRouteItem(fastestRoute: FastestRoute, onClick: () -> Unit) {
    val list = fastestRoute.mediumIconsDuration
    val list2 = fastestRoute.mediumIconsInfo

    val isNeedExpansion = remember { mutableStateOf(false) }
    val animatedScale: Float by animateFloatAsState(targetValue = if (isNeedExpansion.value) 0.9f else 1f)

    Card(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .scale(animatedScale)
            .clickable {
                CoroutineScope(Dispatchers.Main).launch {
                    isNeedExpansion.value = !isNeedExpansion.value
                    delay(100)
                    isNeedExpansion.value = !isNeedExpansion.value
                    onClick()
                }
            },
        backgroundColor = White,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            Modifier
                .padding(10.dp)
        ) {
            ReportIcon(
                Modifier
                    .padding(bottom = 6.dp)
                    .scale(0.8f)
                    .align(Alignment.End)
            )
            MediumIconWithLineLayout(list = list)
            MediumIconWithInfoLayout(list = list2)
            FastestRouteInfoLayout(duration = "35 Mins", fare = 30.0, distance = 4.0)
        }
    }
}

@Composable
fun ReportIcon(modifier: Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        Icon(
            imageVector = Icons.Default.ErrorOutline,
            contentDescription = "",
            Modifier
                .height(20.dp)
                .width(20.dp),
            tint = customGrayColor
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "Report", color = customGrayColor)
    }
}

@Composable
fun FastestRouteInfo(heading: String, info: String) {
    Column() {
        Text(text = heading, fontSize = 11.sp)
        Text(text = info, fontSize = 11.sp, color = buttonOrangeColor, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun FastestRouteInfoLayout(
    duration: String, fare: Double, distance: Double
) {
    Row(
        Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        FastestRouteInfo(heading = "Next Scheduled".uppercase(), info = "12:48 PM")
        FastestRouteInfo(heading = "Estimated Price".uppercase(), info = "Rs $fare")
        FastestRouteInfo(heading = "Travel Time".uppercase(), info = "~ $duration")
        FastestRouteInfo(heading = "Distance".uppercase(), info = "$distance kms")
    }
}

@Composable
fun MediumIconWithLineLayout(list: List<MediumIconWithDuration>) {
    Row(
        Modifier
            .padding(bottom = 8.dp)
    ) {
        list.forEach {
            MediumIconWithLine(
                modifier = Modifier.weight(it.durationInHr), color = it.color, icon = it.mediumIcon
            )
        }
    }
}

@Composable
fun MediumIconWithLine(
    modifier: Modifier, color: Color, icon: ImageVector
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .background(color)
                .fillMaxWidth()
                .height(15.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(lightGrayColor)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = customGrayColor
            )
        }
    }
}

@Composable
fun MediumIconWithInfo(mediumIconInfo: MediumIconInfo) {
    Row() {
        Icon(
            imageVector = mediumIconInfo.mediumIcon,
            contentDescription = "",
            Modifier
                .height(20.dp)
                .width(20.dp),
            tint = customGrayColor
        )
        Text(
            text = mediumIconInfo.info,
            Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 3.dp),
            fontSize = 12.sp
        )
        if (mediumIconInfo.arrowIcon != null) {
            Icon(
                imageVector = Icons.Default.ArrowRightAlt,
                contentDescription = "",
                Modifier
                    .height(20.dp)
                    .width(20.dp),
                tint = customGrayColor
            )
        }
    }
}

@Composable
fun MediumIconWithInfoLayout(list: List<MediumIconInfo>) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        list.forEach {
            MediumIconWithInfo(mediumIconInfo = it)
        }
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "",
            tint = customGrayColor
        )
    }
}
