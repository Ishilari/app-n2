package com.example.aula01lari1711.models

//Define o modelo de dados(título, descrição, categoria, status, carga horária).
data class Atividade(
    val titulo: String,
    val descricao: String,
    val categoria: String,
    val status: Status,
    val cargaHorariaMinutos: Int // novo campo
)

enum class Status {
    PENDENTE,
    VALIDADO
}
