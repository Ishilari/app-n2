package com.example.aula01lari1711

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
