package org.example.domain

@JvmInline
value class SchemaVersion(val value: Int) {

    init {
        require(value >= 0) { "Invalid schema version: $value" }
    }

    override fun toString(): String = value.toString()
}
