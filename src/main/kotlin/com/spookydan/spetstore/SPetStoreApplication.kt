package com.spookydan.spetstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
class SPetStoreApplication

fun main(args: Array<String>) {
    runApplication<SPetStoreApplication>(*args)
}


