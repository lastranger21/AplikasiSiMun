package com.example.aplikasisimun

import android.media.Image
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ServiceUpload {
    @Multipart
    @POST("/upload")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): ResponseBody
}