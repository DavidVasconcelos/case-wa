package br.com.wa.api.controller

import br.com.wa.api.domain.model.User
import br.com.wa.api.domain.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/users")
class UserController(val service: UserService) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): ResponseEntity<User> {
        logger.info("Buscando usuário com id: $id")
        val user = service.getById(id)
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun save(@RequestBody user: User): ResponseEntity<Any> {
        logger.info("Salvando usuário: {}", user)
        val savedUser = service.saveUser(user)
        val location = this.getUri(id = savedUser.id.toString())
        return ResponseEntity.created(location).build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody user: User): ResponseEntity<Any> {
        logger.info("Atualizando usuário: {}", user)
        service.updateUser(id, user)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    private fun getUri(id: String): URI {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/users/{id}")
            .buildAndExpand(id).toUri()
    }
}
