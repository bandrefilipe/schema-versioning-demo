package org.example.infrastructure

import org.example.application.TransactionProvider
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
private class ExposedTransactionProvider : TransactionProvider {

    override fun <R> transactional(statement: () -> R): R = transaction {
        statement.invoke()
    }
}
