package com.kakao.bank.blog.search.infra.search

import com.kakao.bank.blog.search.infra.redis.PopularSearchKeywordRepositoryImpl
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class PopularSearchKeywordRepositoryImplTest(
    private val popularSearchKeywordRepositoryImpl: PopularSearchKeywordRepositoryImpl,
) {
    @Nested
    inner class Search() {
        @Test
        fun `When ~~ `() {
            val blogList =
                popularSearchKeywordRepositoryImpl.update(
                    keyword = "Test",
                )
        }
    }

    @Nested
    inner class Get() {
        @Test
        fun `When ~~ `() {
            val blogList =
                popularSearchKeywordRepositoryImpl.get(
                    size = 10,
                )

            print("blogList is $blogList")
        }
    }
}
