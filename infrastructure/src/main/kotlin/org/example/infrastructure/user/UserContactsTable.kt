package org.example.infrastructure.user

import org.jetbrains.exposed.dao.id.IntIdTable

internal object UserContactsTable : IntIdTable(name = "user_contacts") {
    val userId = uuid(name = "user_id").references(UserTable.id)
    val contact = text(name = "contact")
    val contactType = text(name = "contact_type")
}
