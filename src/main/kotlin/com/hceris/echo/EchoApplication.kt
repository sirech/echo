package com.hceris.echo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EchoApplication

@SuppressWarnings("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<EchoApplication>(*args)
}

