package org.example.application.user

import org.example.application.Command
import org.example.domain.user.UserId

data class FindUserById(
    val id: UserId,
) : Command
