package org.example.application.user

import org.example.application.Command
import org.example.domain.Email
import org.example.domain.user.Username

data class CreateUser(
    val username: Username,
    val email: Email,
) : Command
