package com.kakao.bank.blog.search.api.controllers

import com.kakao.bank.blog.search.api.controllers.dto.ApiResponse
import com.kakao.bank.blog.search.api.controllers.dto.SearchDto
import com.kakao.bank.blog.search.service.SearchService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
class BlogApiController(
    private val searchService: SearchService,
) {
    @GetMapping
    fun search(
        @RequestParam keyword: String,
        @RequestParam sort: SearchDto.SortingParam = SearchDto.SortingParam.accuracy,
        pageable: Pageable = PageRequest.of(1, 10)
    ): ApiResponse<List<SearchDto.BlogResponse>> {
        return ApiResponse.createSuccess(
            searchService.search(
                keyword = keyword,
                sorting = sort.toDomain(),
                pageable = pageable,
            ).map { SearchDto.BlogResponse.of(it) },
        )
    }

    @GetMapping("/popular-keywords")
    fun getPopularSearchKeywords(
        @RequestParam size: Long = 10,
    ): ApiResponse<List<SearchDto.PopularSearchKeywordResponse>> {
        return ApiResponse.createSuccess(
            searchService.getPopularSearchKeywords(
                size,
            ).map { SearchDto.PopularSearchKeywordResponse.of(it) },
        )
    }
}
