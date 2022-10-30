package org.example.application

interface QueryExecutor<in Q: Query, out R> {
    fun execute(query: Q): R
}
