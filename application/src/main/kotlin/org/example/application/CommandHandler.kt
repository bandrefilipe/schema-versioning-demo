package org.example.application

import mu.KotlinLogging

abstract class CommandHandler<in C: Command, R> {

    protected abstract fun onExecution(cmd: C): R

    protected open fun onSuccess(cmd: C, res: R) { /* no-op */ }
    protected open fun onFailure(cmd: C, err: Throwable) { /* no-op */ }
    protected open fun onCompletion(cmd: C, res: Result<R>) { /* no-op */ }

    fun handle(cmd: C): Result<R> {
        val cmdName = cmd::class.java.simpleName
        return kotlin
            .runCatching {
                log.debug { "Starting $cmdName execution" }
                this.onExecution(cmd)
            }
            .onSuccess {
                log.debug { "$cmdName succeeded" }
                try { this.onSuccess(cmd, it) }
                catch (err: Throwable) {
                    log.warn { "Hook $cmdName.onSuccess failed: ${err.message}" }
                    // discard err and continue
                }
            }
            .onFailure {
                log.warn { "$cmdName failed: ${it.message}" }
                try { this.onFailure(cmd, it) }
                catch (err: Throwable) {
                    log.warn { "Hook $cmdName.onFailure failed: ${err.message}" }
                    // discard err and continue
                }
            }
            .also {
                try { this.onCompletion(cmd, it) }
                catch (err: Throwable) {
                    log.warn { "Hook $cmdName.onCompletion failed: ${err.message}" }
                    // discard err and continue
                }
                log.debug { "$cmdName completed" }
            }
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
