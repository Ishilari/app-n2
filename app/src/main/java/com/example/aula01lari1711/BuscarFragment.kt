package com.example.aula01lari1711

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aula01lari1711.R
import com.example.aula01lari1711.CategoriaAdapter
import com.example.aula01lari1711.Categoria
import com.example.aula01lari1711.AtividadeRepository

class BuscarFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: CategoriaAdapter
    private var listaCompleta = listOf<Categoria>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buscar, container, false)

        recyclerView = view.findViewById(R.id.recycler_categorias)
        searchView = view.findViewById(R.id.search_view_categoria)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        listaCompleta = agruparPorCategoria()
        adapter = CategoriaAdapter(listaCompleta)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val texto = newText?.lowercase()?.trim() ?: ""
                val filtrada = listaCompleta.filter {
                    it.nome.lowercase().contains(texto)
                }
                adapter.updateList(filtrada)
                return true
            }
        })

        return view
    }

    private fun agruparPorCategoria(): List<Categoria> {
        return AtividadeRepository.listaAtividades
            .groupBy { it.categoria }
            .map { (categoria, atividades) ->
                val totalMinutos = atividades.sumOf { it.cargaHorariaMinutos }
                Categoria(categoria, totalMinutos)
            }
    }

}
