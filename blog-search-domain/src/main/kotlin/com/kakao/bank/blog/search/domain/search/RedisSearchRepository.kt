package com.kakao.bank.blog.search.domain.search

import com.kakao.bank.blog.search.domain.blog.Blog

interface RedisSearchRepository {
    fun update(
        keyword: String,
        sorting: Sorting,
        page: Int,
        size: Int,
        blogList: List<Blog>,
    )

    fun get(
        keyword: String,
        sorting: Sorting,
        page: Int,
        size: Int,
    ): List<Blog>?
}
