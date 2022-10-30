package org.example

import org.example.application.TransactionProvider
import org.example.application.user.UserService
import org.example.domain.user.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
private class BeanConfiguration {

    @Bean
    fun userService(
        transactionProvider: TransactionProvider,
        userRepository: UserRepository,
    ) = UserService(
        tx = transactionProvider,
        userRepository = userRepository,
    )
}
