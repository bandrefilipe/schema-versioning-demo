package org.example.domain

import java.lang.IllegalArgumentException
import java.util.regex.Pattern

@JvmInline
value class Email(val value: String) {

    init {
        if (PATTERN.matcher(value).matches().not()) {
            throw IllegalArgumentException("Invalid e-mail: $value")
        }
    }

    override fun toString(): String = value

    companion object {
        private const val REGEX: String = """^(\S+)@(\S+)"""
        private val PATTERN = Pattern.compile(REGEX)
    }
}
