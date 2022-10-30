package org.example.application.user.internal

import org.example.application.CommandHandler
import org.example.application.TransactionProvider
import org.example.application.user.CreateUser
import org.example.domain.user.User
import org.example.domain.user.UserId
import org.example.domain.user.UserRepository

internal class CreateUserCommandHandler(
    private val tx: TransactionProvider,
    private val userRepo: UserRepository,
) : CommandHandler<CreateUser, User>() {

    override fun onExecution(cmd: CreateUser): User {
        return tx.transactional {
            val user = User(UserId.random(), cmd.username, cmd.email)
            return@transactional userRepo.save(user)
        }
    }

}
