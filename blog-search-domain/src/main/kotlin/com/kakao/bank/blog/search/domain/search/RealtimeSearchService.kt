package com.kakao.bank.blog.search.domain.search

import com.kakao.bank.blog.search.domain.blog.Blog
import com.kakao.bank.blog.search.utils.CustomExceptions
import kotlinx.coroutines.runBlocking
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RealtimeSearchService(
    private val realtimeSearchRepositories: List<RealtimeSearchRepository>,
) {
    fun search(
        keyword: String,
        sort: Sorting,
        pageable: Pageable,
    ): List<Blog> =
        runBlocking {
            realtimeSearchRepositories.sortedBy { it.getPriority() }.forEach { searchRepository ->
                runCatching {
                    return@runBlocking searchRepository.search(
                        keyword = keyword,
                        sort = sort,
                        page = pageable.pageNumber,
                        size = pageable.pageSize,
                    )
                }.onFailure { e ->
                    // TODO: RealtimeSearchRepository 에서 실패할 경우 서킷브레이커를 넣어서 요청하지 않도록 개선
                    println("search error is $e")
                }
            }
            throw CustomExceptions.ApiException("둘다 실패")
        }
}
