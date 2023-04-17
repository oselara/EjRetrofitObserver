package com.jlara.ejretrofitobserver

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private var _pelisList = MutableLiveData<MutableList<Pelicula>>()
    val pelisList: LiveData<MutableList<Pelicula>>
        get() = _pelisList

    init {
        viewModelScope.launch {
            _pelisList.value = fetchPeliculas()
        }
    }

    private suspend fun fetchPeliculas(): MutableList<Pelicula> {
        val apiKey = "e4b1428dce89b2d8cc48146ae7fc181f"
        return withContext(Dispatchers.IO) {
            val peliList = mutableListOf<Pelicula>()
            val call = service.getPeliculasPopulares(apiKey, 1, peliList)
            val pelis = call.body()
            if (call.isSuccessful) {
                val pagina = pelis?.pagina
                for (pelicula in pelis?.peliculas!!){
                    peliList.add(pelicula)
                }
 //               val peliList = pelis?.peliculas
                val total = pelis.totalPaginas
                val resultados = pelis.totalResultados
            } else {
                // showError()
            }
            peliList
        }
    }
}