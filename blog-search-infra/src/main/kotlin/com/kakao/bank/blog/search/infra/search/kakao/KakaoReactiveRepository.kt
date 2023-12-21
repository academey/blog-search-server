package com.kakao.bank.blog.search.infra.search.kakao

import com.kakao.bank.blog.search.domain.search.SearchRepository
import com.kakao.bank.blog.search.domain.search.SearchVendorType
import com.kakao.bank.blog.search.domain.search.Sorting
import com.kakao.bank.blog.search.domain.search.Sorting.*
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Repository
class KakaoReactiveRepository(
    private val kakaoWebClient: WebClient,
): SearchRepository {
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

    override suspend fun search(
        keyword: String,
        sort: Sorting,
        page: Int,
        size: Int,
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

    override fun getPriority(): Int = SearchVendorType.Kakao.priority
}