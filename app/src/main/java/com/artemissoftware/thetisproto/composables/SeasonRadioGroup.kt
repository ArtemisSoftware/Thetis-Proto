package com.artemissoftware.thetisproto.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.thetisproto.ThetisScreen
import com.artemissoftware.thetisproto.models.SeasonSettings
import com.artemissoftware.thetisproto.models.Seasons

@Composable
fun SeasonRadioGroup(
    onClick: (Seasons) -> Unit,
    seasonSettings: SeasonSettings
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Seasons.values().forEach { season->

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = season == seasonSettings.favorite,
                    onClick = {
                        onClick(season)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = season.toString())
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ThetisPreview() {
    SeasonRadioGroup(
        onClick = {_->},
        seasonSettings = SeasonSettings()
    )
}