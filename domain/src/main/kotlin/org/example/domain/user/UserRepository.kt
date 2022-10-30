package org.example.domain.user

interface UserRepository {

    fun save(entity: User): User
    fun findById(id: UserId): User?
}
