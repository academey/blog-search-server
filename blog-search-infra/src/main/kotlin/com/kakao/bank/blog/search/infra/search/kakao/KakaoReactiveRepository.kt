package com.kakao.bank.blog.search.infra.search.kakao

import com.kakao.bank.blog.search.infra.search.Sorting
import com.kakao.bank.blog.search.infra.search.Sorting.*
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Repository
class KakaoReactiveRepository(
    private val kakaoWebClient: WebClient,
) {
    enum class Sort {
        accuracy,
        recency,
        ;

        companion object {
            fun of(sort: Sorting): Sort =
                when (sort) {
                    정확도순 -> accuracy
                    최신순 -> recency
                }
        }
    }

    suspend fun search(
        keyword: String,
        sort: Sorting,
        page: Long,
        size: Long,
    ) {
        val retrieve =
            kakaoWebClient.get()
                .uri { uriBuilder ->
                    uriBuilder.path("/v2/search/blog")
                        .queryParam("query", keyword)
                        .queryParam("sort", Sort.of(sort))
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build()
                }
                .retrieve()
                .awaitBody<String>()
        print(retrieve)
    }
}
