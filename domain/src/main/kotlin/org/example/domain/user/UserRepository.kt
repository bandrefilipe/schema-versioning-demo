package org.example.domain.user

interface UserRepository {

    fun save(entity: User): UserId
    fun findById(id: UserId): User?
    @Throws(UserNotFoundException::class)
    fun update(entity: User)
}
