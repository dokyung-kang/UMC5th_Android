package com.example.flo

import retrofit2.Call
import retrofit2.http.GET

interface AlbumRetrofitInterfaces {
    @GET("/albums")
    fun getAlbums(): Call<AlbumResponse>
}