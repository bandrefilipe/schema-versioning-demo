package org.example.infrastructure

import mu.KotlinLogging
import org.example.infrastructure.user.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
private class InfrastructureConfiguration(
    dataSource: DataSource
) {

    init {
        log.info { "Connecting to database" }
        Database.connect(dataSource)

        transaction {
            log.info { "Creating database schema" }
            SchemaUtils.create(UserTable)
        }
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
