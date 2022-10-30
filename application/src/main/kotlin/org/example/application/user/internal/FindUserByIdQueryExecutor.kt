package org.example.application.user.internal

import org.example.application.QueryExecutor
import org.example.application.TransactionProvider
import org.example.application.user.FindUserById
import org.example.domain.user.User
import org.example.domain.user.UserRepository

internal class FindUserByIdQueryExecutor(
    private val tx: TransactionProvider,
    private val userRepo: UserRepository,
) : QueryExecutor<FindUserById, User?> {

    override fun execute(query: FindUserById): User? {
        return tx.transactional {
            return@transactional userRepo.findById(query.id)
        }
    }
}
