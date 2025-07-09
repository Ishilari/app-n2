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
    private lateinit var editSenha: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editNome = findViewById(R.id.edit_nome)
        editEmail = findViewById(R.id.edit_email)
        editCargo = findViewById(R.id.edit_cargo)
        editSenha = findViewById(R.id.edit_senha)
        buttonLogin = findViewById(R.id.button_login)

        buttonLogin.setOnClickListener {
            val nome = editNome.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val cargo = editCargo.text.toString().trim()
            val senha = editSenha.text.toString()

            // Verificações
            if (nome.isEmpty() || email.isEmpty() || cargo.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "E-mail inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (senha.length < 6) {
                Toast.makeText(this, "A senha deve ter no mínimo 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Salva os dados no SharedPreferences
            val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
            with(prefs.edit()) {
                putString("nome", nome)
                putString("email", email)
                putString("cargo", cargo)
                putString("senha", senha)
                apply()
            }

            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
            finish() // Volta para a MainActivity
        }
    }
}
