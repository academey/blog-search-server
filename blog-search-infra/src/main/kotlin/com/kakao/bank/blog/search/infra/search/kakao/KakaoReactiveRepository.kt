package com.kakao.bank.blog.search.infra.search.kakao

import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Repository
class KakaoReactiveRepository(
    private val kakaoWebClient: WebClient
) {

    suspend fun search(

    ) {
        val retrieve = kakaoWebClient.get()
            .uri("/")
            .retrieve()
            .awaitBody<String>()
    }
}