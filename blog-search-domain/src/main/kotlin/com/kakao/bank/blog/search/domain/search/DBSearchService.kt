package com.kakao.bank.blog.search.domain.search

import com.kakao.bank.blog.search.domain.blog.Blog
import org.springframework.data.domain.Pageable
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class DBSearchService(
    private val searchResultRepository: SearchResultRepository,
) {
    fun get(
        keyword: String,
        sorting: Sorting,
        pageable: Pageable,
    ) = searchResultRepository.findByKeywordAndSortingAndPageAndSize(
        keyword = keyword,
        sorting = sorting,
        page = pageable.pageNumber,
        size = pageable.pageSize,
    )?.blogList

    @Async
    fun create(
        keyword: String,
        sorting: Sorting,
        pageable: Pageable,
        blogList: List<Blog>,
    ) = searchResultRepository.save(
        SearchResult(
            keyword = keyword,
            sorting = sorting,
            page = pageable.pageNumber,
            size = pageable.pageSize,
            blogList = blogList,
        ),
    )
}
