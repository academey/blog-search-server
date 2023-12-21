package com.kakao.bank.blog.search.infra.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration
import java.time.ZoneOffset

@Configuration
@ConfigurationProperties(prefix = "iamport")
class KakaoConfiguration {
    lateinit var baseUrl: String

    @Bean
    fun kakaoWebClient(webClientBuilder: WebClient.Builder): WebClient =
        webClientBuilder
            .baseUrl(baseUrl)
            .codecs { it.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE) }
            .build()

    companion object {
        val iamportZoneOffset: ZoneOffset = ZoneOffset.of("+9")
        val IAMPORT_API_TIMEOUT: Duration = Duration.ofSeconds(3L)
        private const val MAX_IN_MEMORY_SIZE = 16 * 1024 * 1024
    }
}
