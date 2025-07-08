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
                    Atividade(
                        "Visita à Fábrica da Fiat",
                        "Visita técnica à planta industrial",
                        "Visitas Técnicas",
                        Status.VALIDADO,
                        180
                    ),
                    Atividade(
                        "Curso de Inglês Online",
                        "Curso de 20h na plataforma X",
                        "Curso de Língua Estrangeira",
                        Status.PENDENTE,
                        1200
                    ),
                    Atividade(
                        "Voluntariado em ONG",
                        "Apoio em atividades educacionais",
                        "Trabalho Voluntario",
                        Status.VALIDADO,
                        240
                    ),
                    Atividade(
                        "Mostra de Cinema Francês",
                        "Participação em evento cultural",
                        "Atividades Culturais",
                        Status.PENDENTE,
                        150
                    )
                )
            )
        }

        adapter = AtividadeAdapter(AtividadeRepository.listaAtividades)
        recyclerView.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
