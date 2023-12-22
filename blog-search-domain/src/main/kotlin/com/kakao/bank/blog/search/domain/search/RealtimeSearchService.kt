package com.kakao.bank.blog.search.domain.search

import com.kakao.bank.blog.search.domain.blog.Blog
import com.kakao.bank.blog.search.utils.CustomExceptions
import kotlinx.coroutines.runBlocking
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RealtimeSearchService(
    private val searchRepositories: List<RealtimeSearchRepository>,
) {
    fun search(
        keyword: String,
        sort: Sorting,
        pageable: Pageable,
    ): List<Blog> =
        runBlocking {
            searchRepositories.sortedBy { it.getPriority() }.forEach { searchRepository ->
                return@runBlocking searchRepository.search(
                    keyword = keyword,
                    sort = sort,
                    page = pageable.pageNumber,
                    size = pageable.pageSize,
                )
            }
            throw CustomExceptions.ApiException("둘다 실패")
        }
}
