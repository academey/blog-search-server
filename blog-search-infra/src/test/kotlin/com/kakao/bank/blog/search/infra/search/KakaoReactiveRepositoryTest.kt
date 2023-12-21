package com.kakao.bank.blog.search.infra.search

import com.kakao.bank.blog.search.infra.search.kakao.KakaoReactiveRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class KakaoReactiveRepositoryTest(
    private val kakaoReactiveRepository: KakaoReactiveRepository
) {
    @Test
    fun didid() = runBlocking {
        run {
            kakaoReactiveRepository.search()
        }

    }
}