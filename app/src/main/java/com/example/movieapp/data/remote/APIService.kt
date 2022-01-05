package com.example.movieapp.data.remote

import android.provider.SyncStateContract
import com.example.movieapp.data.model.BaseMovie
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.net.URL

interface APIService {
    @GET(APIUrls.LATEST_MOVIE)
    suspend fun movieList(@Query("page") page:Int):BaseMovie
}