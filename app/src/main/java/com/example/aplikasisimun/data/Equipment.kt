package com.example.aplikasisimun.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Equipment (
    val name: String,
    val description: String,
    val consequence: String,
    val solution: String,
    val photoURL: Int,
    val photoColor: Int,
    ):Parcelable