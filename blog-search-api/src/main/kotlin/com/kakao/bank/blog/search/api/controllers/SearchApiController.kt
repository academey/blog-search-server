package com.kakao.bank.blog.search.api.controllers

import com.kakao.bank.blog.search.api.controllers.dto.ApiResponse
import com.kakao.bank.blog.search.api.controllers.dto.SearchDto
import com.kakao.bank.blog.search.api.controllers.dto.UserDto
import com.kakao.bank.blog.search.domain.user.UserAlertMeansService
import com.kakao.bank.blog.search.domain.user.UserService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/blogs")
class SearchApiController(
) {
    @GetMapping
    fun search(
        @RequestParam keyword: String,
        @RequestParam sort: SearchDto.Sorting,
        pageable: Pageable,
    ): ApiResponse<Page<UserDto.UserRes>> {
        TODO()
//        return ApiResponse.createSuccess(userService.create(requestDto.nickname).let(UserDto.UserRes::of))
    }
}
