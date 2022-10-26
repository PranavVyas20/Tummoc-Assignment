package com.example.tummoc_assignment.ui_components.screen_2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tummoc_assignment.models.routes.Route
import com.example.tummoc_assignment.models.routes.Trail
import com.example.tummoc_assignment.ui.theme.buttonOrangeColor
import com.example.tummoc_assignment.ui.theme.lightGrayColor
import com.example.tummoc_assignment.ui.theme.midLightGrayColor
import com.example.tummoc_assignment.util.Constants

@Composable
fun SelectedRouteItem(selectedRoute: Route, onClick: () -> Unit) {
    val isBusTravelMedium = selectedRoute.medium == Constants.Median.BUS

    val headingText = if (selectedRoute.isFinalRoute != null) {
        "Destination"
    } else if (!isBusTravelMedium) {
        "Source"
    } else {
        "Get in station"
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .clickable {
                onClick()
            }, backgroundColor = lightGrayColor, shape = RoundedCornerShape(15.dp), elevation = 0.dp
    ) {
        SelectedRouteLayut(
            heading = headingText,
            sourceDestination = selectedRoute.sourceTitle,
            getDownStation = if (isBusTravelMedium) selectedRoute.destinationTitle else null,
            mediumInfo = if (isBusTravelMedium) selectedRoute.busRouteDetails!!.routeNumber else null,
            fare = if (isBusTravelMedium) selectedRoute.fare.toInt().toString() else null,
            duration = selectedRoute.formattedDuration,
            distance = selectedRoute.formattedDistance,
            trails = selectedRoute.trails
        )
    }
}

@Composable
fun SelectedRouteLayut(
    heading: String,
    trails: List<Trail>?,
    sourceDestination: String,
    getDownStation: String?,
    mediumInfo: String?,
    fare: String?,
    duration: String,
    distance: String
) {
    val isExpanded = rememberSaveable { mutableStateOf(false) }
    val busIcon = Icons.Filled.DirectionsBus
    val walkIcon = Icons.Filled.DirectionsWalk

    Column(Modifier.padding(10.dp)) {
        Text(text = heading, fontSize = 11.sp, color = midLightGrayColor)
        Text(
            text = sourceDestination,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        MediumInfoLayout(
            icon = if (getDownStation != null) busIcon else walkIcon,
            mediumInfo = mediumInfo,
            fare = fare,
            duration = duration,
            distance = distance
        )
        if (trails != null) {
            Row(
                Modifier
                    .align(Alignment.End)
                    .clickable {
                        isExpanded.value = !isExpanded.value
                    }) {
                Text(text = "Show stops", color = midLightGrayColor)
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
            }
            AnimatedVisibility(visible = isExpanded.value) {
                Column(
                    Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(120.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    trails.forEach {
                        Text(modifier = Modifier.padding(10.dp), text = it.name)
                    }
                }
            }
        }
        if (getDownStation != null) {
            Text(
                text = "Get Down Station",
                fontSize = 11.sp,
                color = midLightGrayColor,
                modifier = Modifier.padding(top = 15.dp)
            )
            Text(
                text = getDownStation, fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun MediumInfoLayout(
    icon: ImageVector, mediumInfo: String?, fare: String?, duration: String, distance: String
) {
    Row(Modifier.horizontalScroll(rememberScrollState())) {
        Box(
            Modifier
                .clip(CircleShape)
                .background(Color.DarkGray)
        ) {
            Icon(imageVector = icon, contentDescription = "", tint = Color.White)
        }
        if (mediumInfo != null) {
            MediumInfoItem(text = mediumInfo, buttonOrangeColor)
        }
        MediumInfoItem(text = duration, Color.Blue)
        MediumInfoItem(text = distance, Color.Black)
        if (fare != null) {
            MediumInfoItem(text = "$fare Rs", Color.Blue)
        }
    }
}

@Composable
private fun MediumInfoItem(text: String, textColor: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.ArrowRightAlt,
            contentDescription = "",
            tint = midLightGrayColor
        )
        Text(text = text, color = textColor, fontWeight = FontWeight.Bold)
    }
}