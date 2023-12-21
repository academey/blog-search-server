package com.kakao.bank.blog.search.api.controllers

import com.kakao.bank.blog.search.api.controllers.dto.ApiResponse
import com.kakao.bank.blog.search.api.controllers.dto.UserDto
import com.kakao.bank.blog.search.domain.user.UserAlertMeansService
import com.kakao.bank.blog.search.domain.user.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserApiController(
    private val userService: UserService,
    private val userAlertMeansService: UserAlertMeansService,
) {
    @PostMapping
    fun create(
        @RequestBody requestDto: UserDto.CreateUserReq,
    ): ApiResponse<UserDto.UserRes> {
        return ApiResponse.createSuccess(userService.create(requestDto.nickname).let(UserDto.UserRes::of))
    }

    @PostMapping("/{userId}/means")
    fun registerMeans(
        @PathVariable userId: Long,
        @Valid @RequestBody requestDto: UserDto.RegisterMeansReq,
    ): ApiResponse<UserDto.UserAlertMeanRes> {
        return ApiResponse.createSuccess(
            userAlertMeansService.createUserAlertMeans(
                type = requestDto.type,
                userId = userId,
                token = requestDto.token,
                channelId = requestDto.channelId,
            ).let(UserDto.UserAlertMeanRes::of),
        )
    }
}
