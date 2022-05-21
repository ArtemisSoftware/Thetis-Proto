package com.artemissoftware.thetisproto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.thetisproto.composables.AlphaCard
import com.artemissoftware.thetisproto.composables.ColorCard
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalMaterialApi
@Composable
fun ThetisScreen() {

    var selectedAlpha by remember  { mutableStateOf(1f) }

    val thetisViewModel: ThetisViewModel = viewModel()
    val context = LocalContext.current
    val state = thetisViewModel.stateFlag


    val color = getColor(state.value.first)
    selectedAlpha = state.value.second

    LaunchedEffect(key1 = true) {
        thetisViewModel.getUserPreferences(context)
    }


    Box(modifier = Modifier.fillMaxSize()) {

        Surface(
            color = color,
            modifier = Modifier
                .fillMaxSize()
                .alpha(selectedAlpha)
        ){}

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Thetis favorite color is ${state.value.first} $selectedAlpha",
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column {
                ColorCard(
                    onClick = { color ->
                        thetisViewModel.saveLanguage(color = color, alpha = selectedAlpha)
                    },
                    color = Color.Magenta,
                    colorName = "Magenta",
                    isSelected = ("Magenta" == state.value.first)
                )

                ColorCard(
                    onClick = { color ->
                        thetisViewModel.saveLanguage(color = color, alpha = selectedAlpha)
                    },
                    color = Color.Cyan,
                    colorName = "Cyan",
                    isSelected = ("Cyan" == state.value.first)
                )
            }


            Spacer(modifier = Modifier.height(32.dp))

            Row {

                AlphaCard(
                    alphaValue = 0.1f,
                    onClick = { alpha->
                        selectedAlpha = alpha
                        thetisViewModel.saveLanguage(color = state.value.first, alpha = alpha)
                    },
                    isSelected = (0.1f == state.value.second)
                )
                AlphaCard(
                    alphaValue = 0.5f,
                    onClick = { alpha->
                        selectedAlpha = alpha
                        thetisViewModel.saveLanguage(color = state.value.first, alpha = alpha)
                    },
                    isSelected = (0.5f == state.value.second)
                )
                AlphaCard(
                    alphaValue = 1f,
                    onClick = { alpha->
                        selectedAlpha = alpha
                        thetisViewModel.saveLanguage(color = state.value.first, alpha = alpha)
                    },
                    isSelected = (1f == state.value.second)
                )
            }
        }
    }


}


private fun getColor(color: String): Color{
    return when (color) {
        "Magenta" -> {
            Color.Magenta
        }
        "Cyan" -> {
            Color.Cyan
        }
        else -> {Color.White}
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun ThetisPreview() {
    ThetisScreen()
}