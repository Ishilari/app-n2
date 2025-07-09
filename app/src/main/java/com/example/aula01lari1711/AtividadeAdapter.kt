package com.example.aula01lari1711.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aula01lari1711.R
import com.example.aula01lari1711.models.Atividade
import com.example.aula01lari1711.models.Status

class AtividadeAdapter(
    private val lista: List<Atividade>
) : RecyclerView.Adapter<AtividadeAdapter.AtividadeViewHolder>() {

    class AtividadeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.text_titulo)
        val descricao: TextView = view.findViewById(R.id.text_descricao)
        val categoria: TextView = view.findViewById(R.id.text_categoria)
        val statusIcon: ImageView = view.findViewById(R.id.image_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtividadeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_atividade, parent, false)
        return AtividadeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AtividadeViewHolder, position: Int) {
        val atividade = lista[position]

        holder.titulo.text = atividade.titulo
        holder.descricao.text = atividade.descricao
        holder.categoria.text = atividade.categoria

        // Define o ícone de acordo com o status
        val icon = when (atividade.status) {
            Status.PENDENTE -> android.R.drawable.presence_away
            Status.VALIDADO -> android.R.drawable.presence_online
        }
        holder.statusIcon.setImageResource(icon)
    }

    override fun getItemCount(): Int = lista.size
}
// esse adapter liga a lista de atividade ao layout item_atividade.xml
