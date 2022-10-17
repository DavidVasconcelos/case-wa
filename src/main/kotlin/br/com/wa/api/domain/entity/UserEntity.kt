package br.com.wa.api.domain.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Int? = null,
    @Column(name = "identificador")
    val identificador: String? = null,
    @Column(name = "nome")
    val nome: String? = null,
    @Column(name = "documento")
    val documento: String? = null,
    @Column(name = "data_criacao")
    val dataCriacao: LocalDateTime? = null,
    @Column(name = "data_atualizacao")
    val dataAtualizacao: LocalDateTime? = null
)
