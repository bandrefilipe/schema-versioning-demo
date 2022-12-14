package org.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
private class Main

fun main(args: Array<String>) {
    SpringApplication.run(Main::class.java, *args)
}
