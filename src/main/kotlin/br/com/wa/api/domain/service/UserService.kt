package br.com.wa.api.domain.service

import br.com.wa.api.domain.entity.UserEntity
import br.com.wa.api.domain.model.User
import br.com.wa.api.exception.BusinessValidationException
import br.com.wa.api.exception.NotFoundException
import br.com.wa.api.extension.unwrap
import br.com.wa.api.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate
import java.time.LocalDateTime
import java.util.UUID

@Service
class UserService(val repository: UserRepository) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun getById(id: Long): User {
        val userEntity = findById(id)
        logger.info("Usuário com identificador ${userEntity.identificador} recuperado com suscesso")
        return User(userEntity)
    }

    fun saveUser(user: User): User {
        try {
            validate(user) {
                validate(User::nome).isNotEmpty()
                validate(User::documento).isNotEmpty()
            }
            val entity = UserEntity(
                identificador = UUID.randomUUID().toString(),
                nome = user.nome,
                documento = user.documento,
                dataCriacao = LocalDateTime.now()
            )
            val savedEntity = repository.save(entity)
            return User(savedEntity)
        } catch (ex: ConstraintViolationException) {
            throw BusinessValidationException("Usuário inválido")
        }
    }

    fun updateUser(id: Long, user: User) {
        var savedUser = findById(id)
        savedUser.nome = user.nome
        savedUser.documento = user.documento
        savedUser.dataAtualizacao = LocalDateTime.now()
        repository.save(savedUser)
    }

    private fun findById(id: Long) = repository.findById(id).unwrap() ?: throw NotFoundException("User not found")
}