package com.example.aula01lari1711

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aula01lari1711.R
import com.example.aula01lari1711.AtividadeAdapter
import com.example.aula01lari1711.Atividade
import com.example.aula01lari1711.Status
import com.example.aula01lari1711.AtividadeRepository

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AtividadeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recycler_atividades)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Se ainda não houver dados no repositório, adiciona dados simulados
        if (AtividadeRepository.listaAtividades.isEmpty()) {
            AtividadeRepository.listaAtividades.addAll(
                listOf(
                    Atividade("Curso de Kotlin", "Curso introdutório", "Cursos", Status.VALIDADO, 120),
                    Atividade("Palestra de Ética", "Evento online", "Palestras", Status.PENDENTE, 90),
                    Atividade("Voluntariado", "Ação social em ONG", "Trabalho voluntário", Status.VALIDADO, 180)
                )
            )
        }


        adapter = AtividadeAdapter(AtividadeRepository.listaAtividades)
        recyclerView.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged() // Atualiza a lista quando voltar para esse fragment
    }
}
