package org.example.infrastructure.user

import mu.KotlinLogging
import org.example.application.user.*
import org.example.domain.Email
import org.example.domain.user.User
import org.example.domain.user.UserId
import org.example.domain.user.Username
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(path = ["/v1/users"])
private class UsersV1RestController(
    private val userService: UserService,
) {

    @PostMapping
    fun createUser(
        @RequestBody form: UserV1Form,
    ): ResponseEntity<User> {
        log.debug { "POST /v1/users : createUser" }
        val username = Username(requireNotNull(form.username))
        val email = Email(requireNotNull(form.email))
        return userService
            .createUser(cmd = CreateUser(username, email))
            .let { user -> ResponseEntity.ok(user) }
    }

    @GetMapping(path = ["/{id}"])
    fun findUserById(
        @PathVariable("id") id: String,
    ): ResponseEntity<User> {
        log.debug { "GET /v1/users/$id : findUserById" }
        val userId = UserId(id)
        return userService
            .findUserById(query = FindUserById(userId))
            ?.let { user -> ResponseEntity.ok(user) }
            ?: ResponseEntity.notFound().build()
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}