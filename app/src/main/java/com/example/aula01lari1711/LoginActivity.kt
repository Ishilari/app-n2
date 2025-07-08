package com.example.aula01lari1711

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editEmail: EditText
    private lateinit var editCargo: EditText
    private lateinit var editSenha: EditText // Adicionando a senha
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editNome = findViewById(R.id.edit_nome)
        editEmail = findViewById(R.id.edit_email)
        editCargo = findViewById(R.id.edit_cargo)
        editSenha = findViewById(R.id.edit_senha) // Pegando a senha
        buttonLogin = findViewById(R.id.button_login)

        buttonLogin.setOnClickListener {
            val nome = editNome.text.toString()
            val email = editEmail.text.toString()
            val cargo = editCargo.text.toString()
            val senha = editSenha.text.toString() // Pegando o valor da senha

            // Armazenando as informações no SharedPreferences
            val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
            with(prefs.edit()) {
                putString("nome", nome)
                putString("email", email)
                putString("cargo", cargo)
                putString("senha", senha) // Salvando a senha
                apply()
            }

            finish() // Volta para a MainActivity após login
        }
    }
}
