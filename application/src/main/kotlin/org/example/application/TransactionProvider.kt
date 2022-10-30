package org.example.application

interface TransactionProvider {
    fun <R> transactional(statement: () -> R): R
}
