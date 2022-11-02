package org.example.infrastructure.user

import org.example.domain.user.User
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class User2UserV1PayloadConverter : Converter<User, UserV1Payload> {

    override fun convert(source: User) = UserV1Payload(
        id = source.id.value.toString(),
        schema = source.schema?.value,
        username = source.username.value,
        email = checkNotNull(source.email?.value) { "Unexpected null attribute: user.email" },
    )
}
