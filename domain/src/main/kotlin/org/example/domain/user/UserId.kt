package org.example.domain.user

import java.util.*

@JvmInline
value class UserId(val value: UUID) {

    constructor(value: String) : this(UUID.fromString(value))

    override fun toString(): String = value.toString()

    companion object {
        fun random(): UserId = UserId(UUID.randomUUID())
    }
}
