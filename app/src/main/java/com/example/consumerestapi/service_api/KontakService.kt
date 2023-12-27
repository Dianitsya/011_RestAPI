package com.example.consumerestapi.service_api

import com.example.consumerestapi.model.Kontak
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface KontakService {
    @Headers(
        "Accept: application/json"
    )
    @GET("/kontak")
    suspend fun getKontak(): List<Kontak>


}