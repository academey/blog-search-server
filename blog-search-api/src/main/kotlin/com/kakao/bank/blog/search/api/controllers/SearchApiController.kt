package com.kakao.bank.blog.search.api.controllers

import com.kakao.bank.blog.search.api.controllers.dto.ApiResponse
import com.kakao.bank.blog.search.api.controllers.dto.SearchDto
import com.kakao.bank.blog.search.service.SearchService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
class SearchApiController(
    private val searchService: SearchService
) {
    @GetMapping
    fun search(
        @RequestParam keyword: String,
        @RequestParam sort: SearchDto.SortingParam,
        pageable: Pageable,
    ): ApiResponse<Page<Long>> {
        searchService.search(
            keyword = keyword,
            sort = sort.toDomain(),
            pageable = pageable
        )
        TODO()
//        return ApiResponse.createSuccess(userService.create(requestDto.nickname).let(UserDto.UserRes::of))
    }
}
