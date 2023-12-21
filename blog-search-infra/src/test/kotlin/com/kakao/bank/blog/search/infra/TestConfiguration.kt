package com.kakao.bank.blog.search.infra

import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ["com.kakao.bank.blog.search.infra"])
class TestConfiguration {
    @Test
    fun hi() {
        println("test")
    }
}
