package com.example.tummoc_assignment.ui_components.screen_1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Train
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TravellingMediumLayout() {
    Row(
        Modifier
            .padding(30.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val busIcon = Icons.Default.DirectionsBus
        val trainIcon = Icons.Default.Train

        val busSelected = remember {
            mutableStateOf(true)
        }

        val metroSelected = remember {
            mutableStateOf(false)
        }

        TravelMediumItem(
            icon = busIcon,
            text = "Bus",
            isSelected = busSelected.value,
            onClick = {
                busSelected.value = true
                metroSelected.value = false
            }
        )
        TravelMediumItem(
            icon = trainIcon,
            text = "Metro",
            isSelected = metroSelected.value,
            onClick = {
                metroSelected.value = true
                busSelected.value = false
            }
        )

    }
}

@Composable
fun TravelMediumItem(
    icon: ImageVector,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val selectedColor = Color.Black
    val unSelectedColor = Color.LightGray

    Row(
        modifier = Modifier.clickable {
           onClick()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = if (isSelected) selectedColor else unSelectedColor
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) selectedColor else unSelectedColor
        )
    }
}

