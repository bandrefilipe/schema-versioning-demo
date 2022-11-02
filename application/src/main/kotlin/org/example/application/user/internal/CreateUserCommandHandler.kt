package org.example.application.user.internal

import org.example.application.CommandHandler
import org.example.application.TransactionProvider
import org.example.application.user.CreateUser
import org.example.domain.SchemaVersion
import org.example.domain.user.User
import org.example.domain.user.UserId
import org.example.domain.user.UserRepository

internal class CreateUserCommandHandler(
    private val tx: TransactionProvider,
    private val userRepo: UserRepository,
) : CommandHandler<CreateUser, User>() {

    override fun onExecution(cmd: CreateUser): User {
        return tx.transactional {
            val user = User(
                id = UserId.random(),
                schema = SchemaVersion(1),
                username = cmd.username,
                email = cmd.email,
                emails = emptySet(),
            )
            return@transactional userRepo.save(user)
        }
    }

}
