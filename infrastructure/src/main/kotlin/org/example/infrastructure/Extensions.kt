package org.example.infrastructure

import mu.KotlinLogging
import org.springframework.core.convert.ConversionService
import kotlin.reflect.KClass

private val log = KotlinLogging.logger {}

fun <T : Any> ConversionService.convert(source: Any, targetType: KClass<T>): T {
    log.debug { "Converting ${source::class.simpleName} into ${targetType.simpleName}" }
    return checkNotNull(this.convert(source, targetType.java))
}
