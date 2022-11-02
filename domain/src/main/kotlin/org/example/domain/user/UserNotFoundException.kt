package org.example.domain.user

class UserNotFoundException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)
