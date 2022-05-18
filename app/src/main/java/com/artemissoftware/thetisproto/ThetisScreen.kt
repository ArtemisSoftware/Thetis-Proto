package com.artemissoftware.thetisproto

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.thetisproto.composables.AlphaCard
import com.artemissoftware.thetisproto.composables.ColorCard

@ExperimentalMaterialApi
@Composable
fun ThetisScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            /*.background(
                if (stateFlag.value) loginColor else logoutColor
            )*/,
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Thetis favorite color is "/*"User Logged in State ${stateFlag.value}"*/,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(24.dp))



        Column {

            ColorCard(
                onClick = {
//                    lifecycleScope.launch {
//                        userRepo?.saveUserLoggedInState(true)
//                    }
                },
                color = Color.Magenta,
                colorName = "Magenta"
            )

            ColorCard(
                onClick = {
//                    lifecycleScope.launch {
//                        userRepo?.saveUserLoggedInState(true)
//                    }
                },
                color = Color.Cyan,
                colorName = "Cyan"
            )

        }


        Spacer(modifier = Modifier.height(32.dp))

        Row {

            AlphaCard(
                alphaValue = "0.5",
                onClick = {}
            )
            AlphaCard(
                alphaValue = "0.5",
                onClick = {}
            )
            AlphaCard(
                alphaValue = "0.5",
                onClick = {}
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun ThetisPreview() {
    ThetisScreen()
}