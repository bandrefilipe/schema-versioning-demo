package org.example.infrastructure.user

import mu.KotlinLogging
import org.example.application.user.CreateUser
import org.example.application.user.FindUserById
import org.example.application.user.UpdateUser
import org.example.application.user.UserService
import org.example.domain.Email
import org.example.domain.user.UserId
import org.example.domain.user.Username
import org.example.infrastructure.convert
import org.springframework.core.convert.ConversionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/v1/users"])
private class UsersV1RestController(
    private val converter: ConversionService,
    private val userService: UserService,
) {

    @PostMapping
    fun createUser(
        @RequestBody form: UserV1Form,
    ): ResponseEntity<UserV1Payload> {
        log.debug { "POST /v1/users : createUser" }
        val username = Username(requireNotNull(form.username))
        val email = Email(requireNotNull(form.email))
        return userService
            .createUser(cmd = CreateUser(username, email))
            .let { user -> converter.convert(user, UserV1Payload::class) }
            .let { payload -> ResponseEntity.ok(payload) }
    }

    @GetMapping(path = ["/{id}"])
    fun findUserById(
        @PathVariable("id") id: String,
    ): ResponseEntity<UserV1Payload> {
        log.debug { "GET /v1/users/$id : findUserById" }
        val userId = UserId(id)
        return userService
            .findUserById(query = FindUserById(userId))
            ?.let { user -> converter.convert(user, UserV1Payload::class) }
            ?.let { payload -> ResponseEntity.ok(payload) }
            ?: ResponseEntity.notFound().build()
    }

    @PutMapping(path = ["/{id}"])
    fun updateUser(
        @PathVariable("id") id: String,
        @RequestBody form: UserV1Form,
    ): ResponseEntity<UserV1Payload> {
        log.debug { "PUT /v1/users/$id : updateUser" }
        val userId = UserId(id)
        val username = Username(requireNotNull(form.username))
        val email = Email(requireNotNull(form.email))
        return userService
            .updateUser(cmd = UpdateUser(userId, username, email))
            .let { user -> converter.convert(user, UserV1Payload::class) }
            .let { payload -> ResponseEntity.ok(payload) }
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
