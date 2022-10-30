package org.example.infrastructure.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class UserV1Form(
    val username: String?,
    val email: String?,
)
