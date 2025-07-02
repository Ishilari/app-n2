package com.example.aula01lari1711

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.aula01lari1711.R
import com.example.aula01lari1711.Atividade
import com.example.aula01lari1711.Status
import com.example.aula01lari1711.AtividadeRepository

class CriarFragment : Fragment() {

    private lateinit var tituloEdit: EditText
    private lateinit var descricaoEdit: EditText
    private lateinit var categoriaEdit: EditText
    private lateinit var quantidadeEdit: EditText
    private lateinit var radioGroupTipo: RadioGroup
    private lateinit var statusGroup: RadioGroup
    private lateinit var salvarButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_criar, container, false)

        tituloEdit = view.findViewById(R.id.edit_titulo)
        descricaoEdit = view.findViewById(R.id.edit_descricao)
        categoriaEdit = view.findViewById(R.id.edit_categoria)
        quantidadeEdit = view.findViewById(R.id.edit_quantidade)
        radioGroupTipo = view.findViewById(R.id.radio_group_tipo)
        statusGroup = view.findViewById(R.id.status_group)
        salvarButton = view.findViewById(R.id.button_salvar)

        salvarButton.setOnClickListener {
            val titulo = tituloEdit.text.toString()
            val descricao = descricaoEdit.text.toString()
            val categoria = categoriaEdit.text.toString()
            val quantidadeStr = quantidadeEdit.text.toString()

            if (titulo.isEmpty() || descricao.isEmpty() || categoria.isEmpty() || quantidadeStr.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantidade = quantidadeStr.toIntOrNull()
            if (quantidade == null || quantidade <= 0) {
                Toast.makeText(requireContext(), "Quantidade inválida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tipoMinutos = when (radioGroupTipo.checkedRadioButtonId) {
                R.id.radio_horas -> quantidade * 60
                else -> quantidade
            }

            val status = when (statusGroup.checkedRadioButtonId) {
                R.id.radio_validado -> Status.VALIDADO
                else -> Status.PENDENTE
            }

            val novaAtividade = Atividade(titulo, descricao, categoria, status, tipoMinutos)
            AtividadeRepository.listaAtividades.add(novaAtividade)

            Toast.makeText(requireContext(), "Atividade salva!", Toast.LENGTH_SHORT).show()

            // Limpa os campos
            tituloEdit.text.clear()
            descricaoEdit.text.clear()
            categoriaEdit.text.clear()
            quantidadeEdit.text.clear()
            radioGroupTipo.clearCheck()
            statusGroup.clearCheck()
        }

        return view
    }
}
