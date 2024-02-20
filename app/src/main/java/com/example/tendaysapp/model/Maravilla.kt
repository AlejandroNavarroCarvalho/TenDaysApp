package com.example.tendaysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Maravilla(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    val dayRes: Int,
    @DrawableRes val imageRes: Int
)