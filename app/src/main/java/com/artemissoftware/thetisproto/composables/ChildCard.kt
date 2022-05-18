package com.artemissoftware.thetisproto.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.thetisproto.Greeting
import com.artemissoftware.thetisproto.R
import com.artemissoftware.thetisproto.ui.theme.ThetisProtoTheme

@Composable
fun ChildCard() {

    Card(
        elevation = 10.dp,
        border = BorderStroke(1.dp, Color.Blue),
        modifier = Modifier.padding(10.dp)
    ) {

        val image: Painter = painterResource(id = R.drawable.ic_launcher_foreground)

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = image,
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(text = "Card with blue border")
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ChildCard()
}