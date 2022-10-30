package org.example.application.user

import org.example.application.Query
import org.example.domain.user.UserId

data class FindUserById(
    val id: UserId,
) : Query
