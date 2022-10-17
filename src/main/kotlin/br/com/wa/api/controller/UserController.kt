package br.com.wa.api.controller

import br.com.wa.api.domain.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("users")
class UserController {

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int): User {
        return User()
    }

    @PostMapping
    fun save(@RequestBody user: User) {
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody user: User) {
    }
}