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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.thetisproto.ThetisScreen

@ExperimentalMaterialApi
@Composable
fun AlphaCard(
    onClick: () -> Unit,
    alphaValue: String
) {

    Card(
        onClick = {

        },
        elevation = 10.dp,
        modifier = Modifier.padding(4.dp)
    ) {

        Row(
            modifier = Modifier.width(100.dp).padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "$alphaValue Alpha",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun AlphaCardPreview() {
    AlphaCard(
        alphaValue = "0.5",
        onClick = {}
    )
}