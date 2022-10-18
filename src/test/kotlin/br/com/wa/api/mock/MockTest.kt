package br.com.wa.api.mock

import br.com.wa.api.domain.model.User
import br.com.wa.api.extension.toStringPattern
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.UUID

@Component
class MockTest {

    fun getMockUser() = User().apply {
        id = (1..1000).random().toLong()
        identificador = UUID.randomUUID().toString()
        nome = "John Doe"
        documento = "999.999.999-99"
        dataCriacao = LocalDateTime.now().toStringPattern()
        dataAtualizacao = LocalDateTime.now().toStringPattern()
    }
}
