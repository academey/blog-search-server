package com.kakao.bank.blog.search.domain.search

import com.kakao.bank.blog.search.domain.blog.Blog
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RedisSearchService(
    private val redisSearchRepository: RedisSearchRepository,
) {
    fun get(
        keyword: String,
        sort: Sorting,
        pageable: Pageable,
    ): List<Blog>? =
        redisSearchRepository.get(
            keyword = keyword,
            sorting = sort,
            page = pageable.pageNumber,
            size = pageable.pageSize,
        )

    fun update(
        keyword: String,
        sort: Sorting,
        pageable: Pageable,
        blogList: List<Blog>,
    ) = redisSearchRepository.update(
        keyword = keyword,
        sorting = sort,
        page = pageable.pageNumber,
        size = pageable.pageSize,
        blogList = blogList,
    )
}
