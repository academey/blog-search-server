package com.kakao.bank.blog.search.service

import com.kakao.bank.blog.search.domain.search.RedisSearchRepository
import com.kakao.bank.blog.search.domain.search.Sorting
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SearchServiceTest(
    private val searchService: SearchService
) {

    @MockkBean
    private lateinit var redisSearchRepository: RedisSearchRepository

    private val keyword = "keyword"
    private val sorting: Sorting = Sorting.최신순
    private val pageable: Pageable = PageRequest.of(1, 10)
    @Nested
    inner class Search() {

        @Test
        fun `When query result is not exist at redis, then search Repository is called`() {
            every {
                redisSearchRepository.get(
                    keyword = keyword,
                    sorting = sorting,
                    page = pageable.pageNumber,
                    size = pageable.pageSize,)
            } returns null

            every {
                redisSearchRepository.update(
                    keyword = keyword,
                    sorting = sorting,
                    page = pageable.pageNumber,
                    size = pageable.pageSize,
                    blogList = any()
                )
            } returns Unit

            searchService.search(
                keyword = keyword,
                sorting = sorting,
                pageable = pageable,
            )

            verify { redisSearchRepository.update(
                keyword = keyword,
                sorting = sorting,
                page = pageable.pageNumber,
                size = pageable.pageSize,
                blogList = any()
            ) }
        }
    }

    @Nested
    inner class GetPopularSearchKeywords() {
        @Test
        fun `When query result is not exist at redis, then search Repository is called`() {
            searchService.getPopularSearchKeywords(10)

        }
    }


}