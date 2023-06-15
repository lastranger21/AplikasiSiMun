package com.example.aplikasisimun.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Education(
     val title: String,
     val description: String,
     val Photo: Int
): Parcelable