package com.artemissoftware.thetisproto

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
import com.artemissoftware.thetisproto.composables.SeasonRadioGroup
import com.artemissoftware.thetisproto.models.Alphas
import com.artemissoftware.thetisproto.models.Colors
import com.artemissoftware.thetisproto.models.Seasons

@ExperimentalMaterialApi
@Composable
fun ThetisScreen() {

    var selectedAlpha by remember  { mutableStateOf(1f) }

    val thetisViewModel: ThetisViewModel = viewModel()
    val context = LocalContext.current
    val state = thetisViewModel.stateFlag
    val favoriteSeason = thetisViewModel.seasonConfig

    val color = getColor(state.value.first)
    selectedAlpha = state.value.second

    LaunchedEffect(key1 = true) {
        thetisViewModel.getUserPreferences(context)
        thetisViewModel.getSeason(context = context)
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

            AvailableColors(
                onClick = {
                    thetisViewModel.saveUserPreferences(color = it, alpha = selectedAlpha)
                },
                selectedColorName = state.value.first
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row {

//                AlphaCard(
//                    alphaValue = 0.1f,
//                    onClick = { alpha->
//                        selectedAlpha = alpha
//                        thetisViewModel.saveUserPreferences(color = state.value.first, alpha = alpha)
//                    },
//                    isSelected = (0.1f == state.value.second)
//                )
//                AlphaCard(
//                    alphaValue = 0.5f,
//                    onClick = { alpha->
//                        selectedAlpha = alpha
//                        thetisViewModel.saveUserPreferences(color = state.value.first, alpha = alpha)
//                    },
//                    isSelected = (0.5f == state.value.second)
//                )
//                AlphaCard(
//                    alphaValue = 1f,
//                    onClick = { alpha->
//                        selectedAlpha = alpha
//                        thetisViewModel.saveUserPreferences(color = state.value.first, alpha = alpha)
//                    },
//                    isSelected = (1f == state.value.second)
//                )


                AvailableAlphas(
                    onClick = {
                        selectedAlpha = it
                        thetisViewModel.saveUserPreferences(color = state.value.first, alpha = it)
                    },
                    selectedAlpha = state.value.second)

            }

            Spacer(modifier = Modifier.height(32.dp))

            SeasonRadioGroup(
                onClick = { season ->
                    thetisViewModel.saveSeason(season)
                },
                seasonSettings =favoriteSeason.value
            )

        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun AvailableColors(
    onClick: (String) -> Unit,
    selectedColorName: String,
){

    Colors.values().take(2).forEach {

        ColorCard(
            onClick = { color ->
                onClick(color)
            },
            color = it.color,
            colorName = it.colorName,
            isSelected = (it.colorName == selectedColorName)
        )
    }
}


@ExperimentalMaterialApi
@Composable
private fun AvailableAlphas(
    onClick: (Float) -> Unit,
    selectedAlpha: Float,
){

    Alphas.values().forEach {

        AlphaCard(
            alphaValue = it.alpha,
            onClick = { alpha ->
                onClick(alpha)
            },
            isSelected = (it.alpha == selectedAlpha)
        )
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