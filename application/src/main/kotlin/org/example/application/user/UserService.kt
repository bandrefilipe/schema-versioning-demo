package org.example.application.user

import org.example.application.TransactionProvider
import org.example.application.user.internal.CreateUserCommandHandler
import org.example.application.user.internal.FindUserByIdQueryExecutor
import org.example.domain.user.User
import org.example.domain.user.UserRepository

class UserService(
    tx: TransactionProvider,
    userRepository: UserRepository,
) {
    private val createUser: CreateUserCommandHandler = CreateUserCommandHandler(tx, userRepository)
    private val findUserById: FindUserByIdQueryExecutor = FindUserByIdQueryExecutor(tx, userRepository)

    fun createUser(cmd: CreateUser): User = createUser.handle(cmd).getOrThrow()
    fun findUserById(query: FindUserById): User? = findUserById.execute(query)
}