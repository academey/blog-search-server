package com.kakao.bank.blog.search.domain.search

import org.springframework.data.domain.Pageable

interface SearchRepository {
    suspend fun search(
        keyword: String,
        sort: Sorting,
        page: Int,
        size: Int,
    )

    fun getPriority(): Int
}
