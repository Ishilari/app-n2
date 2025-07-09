package com.example.aula01lari1711

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.aula01lari1711.R
import com.example.aula01lari1711.models.Atividade
import com.example.aula01lari1711.models.Status
import com.example.aula01lari1711.repository.AtividadeRepository

class CriarFragment : Fragment() {

    private lateinit var tituloEdit: EditText
    private lateinit var descricaoEdit: EditText
    private lateinit var categoriaSpinner: Spinner
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
        categoriaSpinner = view.findViewById(R.id.spinner_categoria)
        quantidadeEdit = view.findViewById(R.id.edit_quantidade)
        radioGroupTipo = view.findViewById(R.id.radio_group_tipo)
        statusGroup = view.findViewById(R.id.status_group)
        salvarButton = view.findViewById(R.id.button_salvar)

        // Define as categorias no Spinner
        val categorias = listOf(
            "Visitas Técnicas",
            "Curso de Língua Estrangeira",
            "Trabalho Voluntario",
            "Atividades Culturais"
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categoriaSpinner.adapter = adapter

        salvarButton.setOnClickListener {
            val titulo = tituloEdit.text.toString()
            val descricao = descricaoEdit.text.toString()
            val categoria = categoriaSpinner.selectedItem.toString()
            val quantidadeStr = quantidadeEdit.text.toString()

            if (titulo.isEmpty() || descricao.isEmpty() || quantidadeStr.isEmpty()) {
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
            quantidadeEdit.text.clear()
            radioGroupTipo.clearCheck()
            statusGroup.clearCheck()
            categoriaSpinner.setSelection(0)
        }

        return view
    }
}
