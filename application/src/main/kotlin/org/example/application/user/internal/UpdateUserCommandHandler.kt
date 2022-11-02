package org.example.application.user.internal

import org.example.application.CommandHandler
import org.example.application.TransactionProvider
import org.example.application.user.UpdateUser
import org.example.domain.user.User
import org.example.domain.user.UserNotFoundException
import org.example.domain.user.UserRepository

class UpdateUserCommandHandler(
    private val tx: TransactionProvider,
    private val userRepo: UserRepository,
) : CommandHandler<UpdateUser, User>() {

    @Throws(UserNotFoundException::class)
    override fun onExecution(cmd: UpdateUser): User {
        return tx.transactional {
            when (val currentUser = userRepo.findById(cmd.userId)) {
                null -> throw UserNotFoundException("User ${cmd.userId} not found")
                else -> return@transactional updateUser(cmd, currentUser)
            }
        }
    }

    private fun updateUser(cmd: UpdateUser, current: User): User {
        val updatedUser = User(
            id = current.id,
            schema = current.schema,
            emails = current.emails,
            // updated values:
            username = cmd.username,
            email = cmd.email,
        )
        return userRepo.update(updatedUser)
    }
}
