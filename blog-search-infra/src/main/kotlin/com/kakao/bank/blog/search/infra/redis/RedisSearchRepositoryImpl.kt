package com.kakao.bank.blog.search.infra.redis

import com.kakao.bank.blog.search.domain.blog.Blog
import com.kakao.bank.blog.search.domain.search.RedisSearchRepository
import com.kakao.bank.blog.search.domain.search.Sorting
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisSearchRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, Any>,
) : RedisSearchRepository {
    override fun update(
        keyword: String,
        sort: Sorting,
        page: Int,
        size: Int,
        blogList: List<Blog>,
    ) {
        val hashCode = getHashCode(keyword, sort, page, size)
        val opsForList = redisTemplate.opsForHash<Int, List<Blog>>()

        return opsForList.put(POPULAR_SEARCH_REDIS_KEY, hashCode, blogList)
    }

    override fun get(
        keyword: String,
        sort: Sorting,
        page: Int,
        size: Int,
    ): List<Blog>? {
        val hashCode = getHashCode(keyword, sort, page, size)
        val opsForList = redisTemplate.opsForHash<Int, List<Blog>>()

        return opsForList.get(POPULAR_SEARCH_REDIS_KEY, hashCode)
    }

    private fun getHashCode(
        keyword: String,
        sort: Sorting,
        page: Int,
        size: Int,
    ): Int {
        return "$keyword:$sort:$page:$size".hashCode()
    }

    companion object {
        const val POPULAR_SEARCH_REDIS_KEY = "blog_search_cache"
    }
}
