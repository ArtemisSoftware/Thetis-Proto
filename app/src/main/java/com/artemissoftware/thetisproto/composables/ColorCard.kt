package com.artemissoftware.thetisproto.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun ColorCard(
    color: Color,
    colorName: String,
    isSelected: Boolean =  false,
    onClick: (String) -> Unit
) {

    Card(
        onClick = {
            onClick(colorName)
        },
        elevation = 10.dp,
        border = BorderStroke(1.dp, if(isSelected) Color.Blue else Color.White),
        modifier = Modifier.padding(4.dp)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(color)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(text = colorName)
        }

    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ColorCard(
        color = Color.Magenta,
        colorName = "Magenta",
        onClick = {_ ->}
    )
}