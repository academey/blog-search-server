package com.kakao.bank.blog.search.service

import com.kakao.bank.blog.search.domain.blog.Blog
import com.kakao.bank.blog.search.domain.blog.BlogVendorType
import com.kakao.bank.blog.search.domain.blog.PopularSearchKeyword
import com.kakao.bank.blog.search.domain.search.RedisSearchRepository
import com.kakao.bank.blog.search.domain.search.SearchResult
import com.kakao.bank.blog.search.domain.search.SearchResultRepository
import com.kakao.bank.blog.search.domain.search.Sorting
import com.kakao.bank.blog.search.infra.redis.PopularSearchKeywordRepositoryImpl
import com.kakao.bank.blog.search.infra.search.kakao.KakaoRealtimeSearchRepository
import com.kakao.bank.blog.search.infra.search.naver.NaverRealtimeSearchRepository
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SearchServiceTest(
    private val searchService: SearchService,
) {
    @MockkBean
    private lateinit var popularSearchKeywordRepositoryImpl: PopularSearchKeywordRepositoryImpl

    @MockkBean
    private lateinit var redisSearchRepository: RedisSearchRepository

    @MockkBean
    private lateinit var searchResultRepository: SearchResultRepository

    @MockkBean
    private lateinit var kakaoRealtimeSearchRepository: KakaoRealtimeSearchRepository

    @MockkBean
    private lateinit var naverRealtimeSearchRepository: NaverRealtimeSearchRepository

    private val keyword = "keyword"
    private val popularSearchKeywordList =
        listOf(
            PopularSearchKeyword(
                keyword = keyword,
                count = 10,
            ),
        )
    private val sorting: Sorting = Sorting.최신순
    private val pageable: Pageable = PageRequest.of(1, 10)
    private val blogList =
        listOf(
            Blog(
                title = "title",
                contents = "contents",
                url = "url",
                blogName = "blogName",
                datetime = "datetime",
                blogVendorType = BlogVendorType.Naver,
            ),
        )
    private val searchResult =
        SearchResult(
            keyword = keyword,
            sorting = sorting,
            page = pageable.pageNumber,
            size = pageable.pageSize,
            blogList = blogList,
        )

    @BeforeEach
    fun beforeAll() {
        every {
            popularSearchKeywordRepositoryImpl.update(any())
        } returns Unit

        every {
            kakaoRealtimeSearchRepository.getPriority()
        } returns BlogVendorType.Kakao.priority

        every {
            naverRealtimeSearchRepository.getPriority()
        } returns BlogVendorType.Naver.priority
    }

    @Nested
    inner class Search() {
        @Test
        fun `When query result is not exist at redis, then search Repository is called`() {
            every {
                popularSearchKeywordRepositoryImpl.get(any())
            } returns popularSearchKeywordList

            every {
                redisSearchRepository.get(
                    keyword = keyword,
                    sorting = sorting,
                    page = pageable.pageNumber,
                    size = pageable.pageSize,
                )
            } returns null

            every {
                redisSearchRepository.update(
                    keyword = keyword,
                    sorting = sorting,
                    page = pageable.pageNumber,
                    size = pageable.pageSize,
                    blogList = any(),
                )
            } returns Unit

            every {
                searchResultRepository.findByKeywordAndSortingAndPageAndSize(
                    keyword = keyword,
                    sorting = sorting,
                    page = pageable.pageNumber,
                    size = pageable.pageSize,
                )
            } returns searchResult

            val result =
                searchService.search(
                    keyword = keyword,
                    sorting = sorting,
                    pageable = pageable,
                )
            result shouldBe blogList

            verify {
                redisSearchRepository.update(
                    keyword = keyword,
                    sorting = sorting,
                    page = pageable.pageNumber,
                    size = pageable.pageSize,
                    blogList = any(),
                )
            }
        }
    }

    @Test
    fun `When query result is not exist at redis and db, then realtime Repository is called`() {
        every {
            popularSearchKeywordRepositoryImpl.get(any())
        } returns popularSearchKeywordList

        every {
            redisSearchRepository.get(
                keyword = keyword,
                sorting = sorting,
                page = pageable.pageNumber,
                size = pageable.pageSize,
            )
        } returns null

        every {
            redisSearchRepository.update(
                keyword = keyword,
                sorting = sorting,
                page = pageable.pageNumber,
                size = pageable.pageSize,
                blogList = any(),
            )
        } returns Unit

        every {
            searchResultRepository.findByKeywordAndSortingAndPageAndSize(
                keyword = keyword,
                sorting = sorting,
                page = pageable.pageNumber,
                size = pageable.pageSize,
            )
        } returns null

        every {
            searchResultRepository.save(
                any(),
            )
        } returns searchResult

        every {
            searchResultRepository.findByKeywordAndSortingAndPageAndSize(
                keyword = keyword,
                sorting = sorting,
                page = pageable.pageNumber,
                size = pageable.pageSize,
            )
        } returns null

        coEvery {
            kakaoRealtimeSearchRepository.search(
                keyword = keyword,
                sorting = sorting,
                page = pageable.pageNumber,
                size = pageable.pageSize,
            )
        } returns blogList

        val result =
            searchService.search(
                keyword = keyword,
                sorting = sorting,
                pageable = pageable,
            )
        result shouldBe blogList

        verify {
            redisSearchRepository.update(
                keyword = keyword,
                sorting = sorting,
                page = pageable.pageNumber,
                size = pageable.pageSize,
                blogList = any(),
            )
        }
    }
}
