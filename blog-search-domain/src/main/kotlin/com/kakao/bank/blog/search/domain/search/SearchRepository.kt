package com.kakao.bank.blog.search.domain.search

import com.kakao.bank.blog.search.domain.blog.Blog

interface SearchRepository {
    suspend fun search(
        keyword: String,
        sort: Sorting,
        page: Int,
        size: Int,
    ): List<Blog>

    fun getPriority(): Int
}
