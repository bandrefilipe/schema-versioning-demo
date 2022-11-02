package org.example.infrastructure.user

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include

@JsonInclude(Include.NON_NULL)
internal data class UserV1Payload(
    val id: String,
    val schema: Int?,
    val username: String,
    val email: String,
)
