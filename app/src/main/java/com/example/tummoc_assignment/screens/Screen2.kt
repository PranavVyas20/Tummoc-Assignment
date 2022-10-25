package com.example.tummoc_assignment.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Screen2() {
    bottomSheet()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun bottomSheet() {
    BottomSheetScaffold(sheetContent = { Text(text = "Sheet")}, sheetBackgroundColor = Color.Blue) {

    }
}
