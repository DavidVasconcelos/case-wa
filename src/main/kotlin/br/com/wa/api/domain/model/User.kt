package br.com.wa.api.domain.model

import br.com.wa.api.domain.entity.UserEntity
import br.com.wa.api.extension.toStringPattern
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    var id: Long? = null,
    var identificador: String? = null,
    var nome: String? = null,
    var documento: String? = null,
    var dataCriacao: String? = null,
    var dataAtualizacao: String? = null
) {
    constructor(entity: UserEntity) : this() {
        this.id = entity.id
        this.identificador = entity.identificador
        this.nome = entity.nome
        this.documento = entity.documento
        this.dataCriacao = entity.dataCriacao?.toStringPattern()
        this.dataAtualizacao = entity.dataAtualizacao?.toStringPattern()
    }

}
