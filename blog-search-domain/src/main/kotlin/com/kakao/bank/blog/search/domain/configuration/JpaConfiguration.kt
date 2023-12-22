package com.kakao.bank.blog.search.domain.configuration

import com.kakao.bank.blog.search.domain.search.SearchResult
import com.kakao.bank.blog.search.domain.search.SearchResultRepository
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.time.OffsetDateTime
import java.util.Optional

@Configuration
@EnableJpaAuditing(
    dateTimeProviderRef = "utcDateTimeProvider",
)
@EntityScan(
    basePackageClasses = [
        SearchResult::class,
    ],
)
@EnableJpaRepositories(
    basePackageClasses = [
        SearchResultRepository::class,
    ],
)
class JpaConfiguration {
    @Bean
    fun utcDateTimeProvider(): DateTimeProvider = DateTimeProvider { Optional.of(OffsetDateTime.now()) }
}
