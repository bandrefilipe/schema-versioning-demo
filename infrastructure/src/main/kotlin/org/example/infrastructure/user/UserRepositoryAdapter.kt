package org.example.infrastructure.user

import org.example.domain.Email
import org.example.domain.user.User
import org.example.domain.user.UserId
import org.example.domain.user.UserRepository
import org.example.domain.user.Username
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Component

@Component
private class UserRepositoryAdapter : UserRepository {

    override fun save(entity: User): User {
        UserTable.insert {
            it[id] = entity.id.value
            it[username] = entity.username.value
            it[email] = entity.email.value
        }
        return entity
    }

    override fun findById(id: UserId): User? {
        return UserTable.select { UserTable.id eq id.value }
            .map(::fromRow)
            .first()
    }

    private fun fromRow(row: ResultRow): User = User(
        UserId(row[UserTable.id].value),
        Username(row[UserTable.username]),
        Email(row[UserTable.email]),
    )
}
