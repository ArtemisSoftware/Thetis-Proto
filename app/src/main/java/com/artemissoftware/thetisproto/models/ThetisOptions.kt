package com.artemissoftware.thetisproto.models

import androidx.compose.ui.graphics.Color

enum class Colors(val color: Color, val colorName: String) {

    MAGENTA(Color.Magenta, "Magenta"),
    CYAN(Color.Cyan, "Cyan"),
    DEFAULT(Color.White, "White")
}

enum class Alphas(val alpha: Float) {

    ALPHA_01(0.1f),
    ALPHA_05(0.5f),
    ALPHA_1(1f)
}

enum class Seasons {
    SPRING, SUMMER, AUTUMN, WINTER
}