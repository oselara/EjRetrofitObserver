package com.jlara.ejretrofitobserver

data class Pelicula(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String
): java.io.Serializable