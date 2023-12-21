package com.kakao.bank.blog.search.domain.search

import kotlinx.coroutines.runBlocking
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RealtimeSearchService(
    private val searchRepositories: List<SearchRepository>,
) {
    fun search(
        keyword: String,
        sort: Sorting,
        pageable: Pageable,
    ) = runBlocking {
        searchRepositories.sortedBy { it.getPriority() }.forEach { searchRepository ->
            searchRepository.search(
                keyword = keyword,
                sort = sort,
                page = pageable.pageNumber,
                size = pageable.pageSize,
            )

        }
    }
}
