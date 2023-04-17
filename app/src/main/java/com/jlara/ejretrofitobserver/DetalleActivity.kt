package com.jlara.ejretrofitobserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jlara.ejretrofitobserver.databinding.ActivityDetalleBinding
import com.squareup.picasso.Picasso

class DetalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var pelicula = intent.getSerializableExtra("pelicula",Pelicula::class.java) as Pelicula
        Picasso.get().load("https://image.tmdb.org/t/p/w500${pelicula.poster_path}").into(binding.ivPelicula)
        binding.tvTitulo.text = pelicula.title
        binding.tvOverview.text = pelicula.overview
    }
}