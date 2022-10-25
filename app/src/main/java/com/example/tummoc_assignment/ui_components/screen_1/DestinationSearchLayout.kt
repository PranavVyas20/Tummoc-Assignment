package com.example.tummoc_assignment.ui_components.screen_1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tummoc_assignment.ui.theme.*

@Preview
@Composable
fun DestinationSearchLayout() {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(5.dp)
    ) {
        Column() {
            SourceDestinationItem(
                isDestination = true,
                heading = "Source",
                dotColor = buttonOrangeColor,
                text = "Source Destination"
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(lightGrayColor)
            )
            SourceDestinationItem(
                heading = "Destination",
                dotColor = walkMediumColor,
                text = "Destination"
            )
        }
    }
}

@Composable
fun SourceDestinationItem(
    heading: String,
    dotColor: Color,
    text: String,
    isDestination: Boolean = false
) {
    val searchText = remember{mutableStateOf(text)}

    Row(
        Modifier.background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIos,
            tint = if (!isDestination) Color.Transparent else Color.Gray,
            contentDescription = "",
            modifier = Modifier.padding(start = 10.dp)
        )
        Icon(
            modifier = Modifier.scale(0.3f),
            imageVector = Icons.Filled.Circle,
            contentDescription = "",
            tint = dotColor
        )
        TextField(
            value = searchText.value,
            onValueChange = {searchText.value = it},
            label = { Text(text = heading) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                val clicked = remember {
                    mutableStateOf(false)
                }
                IconButton(
                    onClick = { clicked.value = !clicked.value }
                ) {
                    if (clicked.value) Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "",
                        tint = Color.Red
                    ) else Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "")
                }
            }
        )
    }
}