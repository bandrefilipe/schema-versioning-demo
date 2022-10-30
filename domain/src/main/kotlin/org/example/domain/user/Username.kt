package org.example.domain.user

import java.lang.IllegalArgumentException
import java.util.regex.Pattern

@JvmInline
value class Username(val value: String) {

    init {
        if (PATTERN.matcher(this.value).matches().not()) {
            throw IllegalArgumentException("Invalid username: $value")
        }
    }

    override fun toString(): String = value

    companion object {
        private val REGEX = """^[A-Za-z]\w{3,}"""
        private val PATTERN = Pattern.compile(REGEX)
    }
}
