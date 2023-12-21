package com.kakao.bank.blog.search.service

import com.kakao.bank.blog.search.domain.search.RealtimeSearchService
import com.kakao.bank.blog.search.domain.search.Sorting
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val realtimeSearchService: RealtimeSearchService
) {
    fun search(
        keyword: String,
        sort: Sorting,
        pageable: Pageable
    ) {
        realtimeSearchService.search(
            keyword,
            sort,
            pageable,
        )
    }
}
