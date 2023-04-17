package com.jlara.ejretrofitobserver

import com.google.gson.annotations.SerializedName

data class ResultadoPeliculas(
    @SerializedName("page") val pagina: Int,
    @SerializedName("results") val peliculas: List<Pelicula>,
    @SerializedName("total_results") val totalResultados: Int,
    @SerializedName("total_pages") val totalPaginas: Int
)
