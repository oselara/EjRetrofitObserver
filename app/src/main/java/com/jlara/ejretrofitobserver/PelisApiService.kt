package com.jlara.ejretrofitobserver

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PelisApiService {

    @GET("movie/popular")
    suspend fun getPeliculasPopulares(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("results") results: List<Pelicula>
    ): Response<ResultadoPeliculas>

    @GET("movie/upcoming")
    suspend fun getProximasPeliculas(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("results") results: List<Pelicula>
    ): Response<ResultadoPeliculas>
}

    private var retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    var service: PelisApiService = retrofit.create(PelisApiService::class.java)
