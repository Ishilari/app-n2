package com.example.aula01lari1711.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aula01lari1711.R
import com.example.aula01lari1711.models.Categoria

class CategoriaAdapter(private var categorias: List<Categoria>) :
    RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {

    class CategoriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeText: TextView = view.findViewById(R.id.text_categoria_nome)
        val totalText: TextView = view.findViewById(R.id.text_categoria_total)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categoria, parent, false)
        return CategoriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = categorias[position]
        holder.nomeText.text = categoria.nome

        // Formatar minutos para "Xh Ymin" ou só "Ymin"
        val horas = categoria.minutosTotais / 60
        val minutos = categoria.minutosTotais % 60

        val tempoFormatado = when {
            horas > 0 && minutos > 0 -> "${horas}h ${minutos}min"
            horas > 0 -> "${horas}h"
            else -> "${minutos}min"
        }

        holder.totalText.text = "Total: $tempoFormatado"
    }

    override fun getItemCount() = categorias.size

    fun updateList(novaLista: List<Categoria>) {
        categorias = novaLista
        notifyDataSetChanged()
    }
}
