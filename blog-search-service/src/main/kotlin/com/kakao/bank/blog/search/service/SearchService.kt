package com.kakao.bank.blog.search.service

import com.kakao.bank.blog.search.domain.blog.Blog
import com.kakao.bank.blog.search.domain.blog.PopularSearchKeyword
import com.kakao.bank.blog.search.domain.blog.PopularSearchKeywordRepository
import com.kakao.bank.blog.search.domain.search.RealtimeSearchService
import com.kakao.bank.blog.search.domain.search.Sorting
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val popularSearchKeywordRepository: PopularSearchKeywordRepository,
    private val realtimeSearchService: RealtimeSearchService,
) {
    fun search(
        keyword: String,
        sort: Sorting,
        pageable: Pageable,
    ): List<Blog> {
//      keyword 에 대해서 캐싱하는 로직
        popularSearchKeywordRepository.update(keyword)


//      redis 에 있는지 확인

//      DB 에 있는지 확인
//      없으면 그때 가서 검색
        return realtimeSearchService.search(
            keyword,
            sort,
            pageable,
        )
    }

    fun getPopularSearchKeywords(
        size: Long
    ): List<PopularSearchKeyword> {
        return popularSearchKeywordRepository.get(size)
    }
}
