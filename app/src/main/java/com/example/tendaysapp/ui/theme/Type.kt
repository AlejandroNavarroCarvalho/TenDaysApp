package com.example.tendaysapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tendaysapp.R

val Unbounded = FontFamily(
    Font(R.font.unbounded_light, FontWeight.Light),
    Font(R.font.unbounded_medium, FontWeight.Medium)
)

val Arizona = FontFamily(
    Font(R.font.arizonia_regular, FontWeight.Normal)
)

val Teko = FontFamily(
    Font(R.font.teko_medium, FontWeight.Medium)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Teko,
        fontWeight = FontWeight.Medium,
        fontSize = 40.sp
    ),
    displayLarge = TextStyle(
        fontFamily = Arizona,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Unbounded,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Unbounded,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    )
)