package com.example.aplikasisimun.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mountain (
    val name: String,
    val description: String,
    val location: String,
    val track: String,
    val mdpl: String,
    val tips: String,
    val photo: Int,

): Parcelable