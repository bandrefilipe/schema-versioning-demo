package org.example.application.user.internal

import org.example.application.CommandHandler
import org.example.application.TransactionProvider
import org.example.application.user.FindUserById
import org.example.domain.user.User
import org.example.domain.user.UserRepository

internal class FindUserByIdCommandHandler(
    private val tx: TransactionProvider,
    private val userRepo: UserRepository,
) : CommandHandler<FindUserById, User?>() {

    override fun onExecution(cmd: FindUserById): User? {
        return tx.transactional {
            return@transactional userRepo.findById(cmd.id)
        }
    }
}
