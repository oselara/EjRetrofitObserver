package com.jlara.ejretrofitobserver

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jlara.ejretrofitobserver.PeliculaAdapter.*
import com.jlara.ejretrofitobserver.databinding.ItemPeliculaBinding
import com.squareup.picasso.Picasso

class PeliculaAdapter: ListAdapter<Pelicula,PeliViewHolder>(DiffCallBack) {
    companion object DiffCallBack: DiffUtil.ItemCallback<Pelicula>(){
        override fun areItemsTheSame(oldItem: Pelicula, newItem: Pelicula): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pelicula, newItem: Pelicula): Boolean {
            return oldItem == newItem
        }
    }

    lateinit var onItemClickListener: (Pelicula)->Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliViewHolder {
        val binding = ItemPeliculaBinding.inflate(LayoutInflater.from(parent.context))
        return PeliViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeliViewHolder, position: Int) {
        val pelicula = getItem(position)
        holder.bind(pelicula)
    }

    inner class PeliViewHolder(private val binding: ItemPeliculaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pelicula: Pelicula) {
           binding.tvPeliculaTitulo.text = pelicula.title
           Picasso.get().load("https://image.tmdb.org/t/p/w500${pelicula.poster_path}").into(binding.ivPeliculaPoster)
            binding.root.setOnClickListener{
                onItemClickListener(pelicula)
            }
    }
    }
}