package org.example.infrastructure.user

import mu.KotlinLogging
import org.example.domain.Email
import org.example.domain.SchemaVersion
import org.example.domain.user.User
import org.example.domain.user.UserId
import org.example.domain.user.UserRepository
import org.example.domain.user.Username
import org.jetbrains.exposed.sql.*
import org.springframework.stereotype.Component

@Component
private class UserRepositoryAdapter : UserRepository {

    override fun save(entity: User): UserId {
        log.debug { "Saving User ${entity.id}"}
        return UserTable.insertAndGetId {
            it[id] = entity.id.value
            it[username] = entity.username.value
            it[email] = entity.email?.value
        }.let { id -> UserId(id.value) }
    }

    override fun findById(id: UserId): User? {
        log.debug { "Finding User $id" }
        val rows = UserTable
            .leftJoin(UserContactsTable)
            .select { UserTable.id eq id.value }
            .toList()
        val emails = lazy(LazyThreadSafetyMode.NONE) { this.mapEmails(rows) }
        return rows.firstOrNull()?.let { this.mapUser(it, emails.value) }
    }

    override fun update(entity: User) {
        log.debug { "Updating User ${entity.id}" }
        UserTable.update {
            it[username] = entity.username.value
            it[email] = entity.email?.value
        }
    }

    private fun mapEmails(rows: Iterable<ResultRow>): Set<Email> {
        return rows
            .filter { it[UserContactsTable.contactType] == "EMAIL" }
            .map { Email(it[UserContactsTable.contact]) }
            .toSet()
    }

    private fun mapUser(row: ResultRow, emails: Set<Email>): User = User(
        id = UserId(row[UserTable.id].value),
        schema = row[UserTable.schema]?.let { SchemaVersion(it) },
        username = Username(row[UserTable.username]),
        email = row[UserTable.email]?.let { Email(it) },
        emails = emails,
    )

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
