package com.jlara.ejretrofitobserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jlara.ejretrofitobserver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPeliculas.layoutManager = LinearLayoutManager(this)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val adapter = PeliculaAdapter()
        viewModel.pelisList.observe(this, Observer { pelisList ->
            adapter.submitList(pelisList)
            handleEmptyView(pelisList)
        })

        binding.rvPeliculas.adapter = adapter
        adapter.onItemClickListener = {
            val intent = Intent(this,DetalleActivity::class.java)
            intent.putExtra("pelicula",it)
            startActivity(intent)
        }

                          //service.getPeliculasPopulares()
    }

    private fun handleEmptyView(pelisList: MutableList<Pelicula>) {
        if (pelisList.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.tvEmpty.visibility = View.GONE
        }
    }
}