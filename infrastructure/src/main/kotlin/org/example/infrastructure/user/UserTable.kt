package org.example.infrastructure.user

import org.jetbrains.exposed.dao.id.UUIDTable

internal object UserTable : UUIDTable() {
    val username = text(name = "username").uniqueIndex()
    val email = text(name = "email").uniqueIndex()
}
