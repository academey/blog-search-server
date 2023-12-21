package com.kakao.bank.blog.search.infra.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@ConfigurationProperties(prefix = "naver")
class NaverConfiguration {
    lateinit var baseUrl: String
    lateinit var clientId: String
    lateinit var clientSecret: String

    @Bean
    fun naverWebClient(webClientBuilder: WebClient.Builder): WebClient =
        webClientBuilder
            .baseUrl(baseUrl)
            .defaultHeader("X-Naver-Client-Id", clientId)
            .defaultHeader("X-Naver-Client-Secret", clientSecret)
            .build()
}
