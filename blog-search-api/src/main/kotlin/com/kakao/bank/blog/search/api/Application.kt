/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.kakao.bank.blog.search.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(basePackages = ["com.kakao.bank.blog.search.domain"])
@EnableJpaRepositories(basePackages = ["com.kakao.bank.blog.search"])
@SpringBootApplication(scanBasePackages = ["com.kakao.bank.blog"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}