package com.example.moodcrafter.model

import android.annotation.SuppressLint
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Parcelize
@Entity(tableName = "register")
data class Register(
    @SuppressLint("KotlinNullnessAnnotation") @PrimaryKey @NonNull val titulo: String,
    @ColumnInfo("eleccion1") val eleccion1: String?,
    @ColumnInfo("eleccion2") val eleccion2: String?,
    @ColumnInfo("eleccion3") val eleccion3: String?
) : Parcelable