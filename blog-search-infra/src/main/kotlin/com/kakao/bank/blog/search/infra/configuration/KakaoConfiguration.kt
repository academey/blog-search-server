package com.kakao.bank.blog.search.infra.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@ConfigurationProperties(prefix = "kakao")
class KakaoConfiguration {
    lateinit var baseUrl: String
    lateinit var restApiKey: String

    @Bean
    fun kakaoWebClient(webClientBuilder: WebClient.Builder): WebClient =
        webClientBuilder
            .baseUrl(baseUrl)
            .defaultHeader("Authorization", "KakaoAK $restApiKey")
            .build()
}
