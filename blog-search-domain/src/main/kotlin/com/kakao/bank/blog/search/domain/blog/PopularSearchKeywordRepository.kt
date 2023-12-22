package com.kakao.bank.blog.search.domain.blog

interface PopularSearchKeywordRepository {
    fun get(size: Long): List<PopularSearchKeyword>

    suspend fun update(keyword: String)
}
