package org.example.domain.user

import org.example.domain.Email
import org.example.domain.SchemaVersion

class User(
    val id: UserId,
    val username: Username,
    val email: Email?,

    val schema: SchemaVersion? = null,
    val emails: Set<Email>,
)
