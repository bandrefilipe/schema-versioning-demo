package org.example.application.user

import org.example.application.Command
import org.example.domain.Email
import org.example.domain.user.UserId
import org.example.domain.user.Username

data class UpdateUser(
    val userId: UserId,
    val username: Username,
    val email: Email,
) : Command
