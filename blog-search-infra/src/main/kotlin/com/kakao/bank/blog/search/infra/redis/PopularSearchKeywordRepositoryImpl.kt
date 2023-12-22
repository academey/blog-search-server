package com.kakao.bank.blog.search.infra.redis

import com.kakao.bank.blog.search.domain.blog.PopularSearchKeyword
import com.kakao.bank.blog.search.domain.blog.PopularSearchKeywordRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.stereotype.Repository
import java.util.stream.Collectors

@Repository
class PopularSearchKeywordRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, String>,
) :
    PopularSearchKeywordRepository {
    override fun get(size: Long): List<PopularSearchKeyword> {
        val key = POPULAR_SEARCH_REDIS_KEY
        val zSetOperations: ZSetOperations<String, String> = redisTemplate.opsForZSet()
        val typedTuples = zSetOperations.reverseRangeWithScores(key, 0, size)

        return typedTuples!!.stream().map {
            PopularSearchKeyword(
                keyword = it.value!!,
                count = it.score!!.toInt(),
            )
        }.collect(Collectors.toList())
    }

    override fun update(keyword: String) {
        val score = 0.0
        redisTemplate.opsForZSet().incrementScore(POPULAR_SEARCH_REDIS_KEY, keyword, 1.0)
        redisTemplate.opsForZSet().incrementScore(POPULAR_SEARCH_REDIS_KEY, keyword, score)
    }

    companion object {
        const val POPULAR_SEARCH_REDIS_KEY = "popular_search"
    }
}
