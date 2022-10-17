package br.com.wa.api.domain.model

import java.time.LocalDateTime

data class User(
    val id: Int? = null,
    val identificador: String? = null,
    val nome: String? = null,
    val documento: String? = null,
    val dataCriacao: LocalDateTime? = null,
    val dataAtualizacao: LocalDateTime? = null
)
