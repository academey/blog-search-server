package com.kakao.bank.blog.search.infra.search

import com.kakao.bank.blog.search.domain.search.Sorting
import com.kakao.bank.blog.search.infra.search.kakao.KakaoReactiveRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class KakaoReactiveRepositoryTest(
    private val kakaoReactiveRepository: KakaoReactiveRepository,
) {
    @Nested
    inner class Search() {
        @Test
        fun `When ~~ `() {
            runBlocking {
                run {
                    kakaoReactiveRepository.search(
                        keyword = "Test",
                        sort = Sorting.정확도순,
                        page = 1,
                        size = 10,
                    )
                }
            }
        }
    }
}
