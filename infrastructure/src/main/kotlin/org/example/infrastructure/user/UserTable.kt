package org.example.infrastructure.user

import org.jetbrains.exposed.dao.id.UUIDTable

internal object UserTable : UUIDTable() {
    val schema = integer(name = "schema").nullable()
    val username = text(name = "username")
    val email = text(name = "email").nullable()
}
