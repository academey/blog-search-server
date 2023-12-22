package com.kakao.bank.blog.search.infra.search.naver

import com.kakao.bank.blog.search.domain.blog.Blog
import com.kakao.bank.blog.search.domain.blog.BlogVendorType
import com.kakao.bank.blog.search.domain.search.RealtimeSearchRepository
import com.kakao.bank.blog.search.domain.search.Sorting
import com.kakao.bank.blog.search.domain.search.Sorting.*
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Repository
class NaverReactiveRepository(
    private val naverWebClient: WebClient,
) : RealtimeSearchRepository {
    enum class Sort {
        sim,
        date,
        ;

        companion object {
            fun of(sort: Sorting): Sort =
                when (sort) {
                    정확도순 -> sim
                    최신순 -> date
                }
        }
    }

    override suspend fun search(
        keyword: String,
        sort: Sorting,
        page: Int,
        size: Int,
    ): List<Blog> =
        naverWebClient.get()
            .uri { uriBuilder ->
                uriBuilder.path("/v1/search/blog.json")
                    .queryParam("query", keyword)
                    .queryParam("sort", Sort.of(sort))
                    .queryParam("start", page)
                    .queryParam("display", size)
                    .build()
            }
            .retrieve()
            .awaitBody<NaverSearchBlogResult>()
            .getBlogs()

    suspend fun searchTest(
        keyword: String,
        sort: Sorting,
        page: Int,
        size: Int,
    ): String =
        naverWebClient.get()
            .uri { uriBuilder ->
                uriBuilder.path("/v1/search/blog.json")
                    .queryParam("query", keyword)
                    .queryParam("sort", Sort.of(sort))
                    .queryParam("start", page)
                    .queryParam("display", size)
                    .build()
            }
            .retrieve()
            .awaitBody<String>()

    override fun getPriority(): Int = BlogVendorType.Naver.priority
}
