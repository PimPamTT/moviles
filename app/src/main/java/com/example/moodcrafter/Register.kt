package com.example.moodcrafter

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Register(
    val titulo: String?,
    val eleccion1: String?,
    val eleccion2: String?,
    val eleccion3: String?
) : Parcelable