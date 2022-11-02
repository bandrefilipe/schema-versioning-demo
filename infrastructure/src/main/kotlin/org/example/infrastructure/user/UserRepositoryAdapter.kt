package org.example.infrastructure.user

import org.example.domain.Email
import org.example.domain.SchemaVersion
import org.example.domain.user.User
import org.example.domain.user.UserId
import org.example.domain.user.UserRepository
import org.example.domain.user.Username
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Component

@Component
private class UserRepositoryAdapter : UserRepository {

    override fun save(entity: User): User {
        UserTable.insert {
            it[id] = entity.id.value
            it[username] = entity.username.value
            it[email] = entity.email?.value
        }
        return entity
    }

    override fun findById(id: UserId): User? {
        val rows = UserTable
            .leftJoin(UserContactsTable)
            .select { UserTable.id eq id.value }
            .toList()
        val emails = lazy(LazyThreadSafetyMode.NONE) { this.mapEmails(rows) }
        return rows.firstOrNull()?.let { this.mapUser(it, emails.value) }
    }

    override fun update(entity: User): User {
        UserTable.update {
            it[username] = entity.username.value
            it[email] = entity.email?.value
        }
        return entity
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
}
