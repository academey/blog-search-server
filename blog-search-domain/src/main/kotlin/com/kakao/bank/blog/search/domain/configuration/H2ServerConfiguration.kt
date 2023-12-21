package com.kakao.bank.blog.search.domain.configuration

import org.h2.tools.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.SQLException

@Configuration
class H2ServerConfiguration {
    @Bean
    @Throws(SQLException::class)
    fun h2TcpServer(): Server {
        return Server.createTcpServer().start()
    }
}
