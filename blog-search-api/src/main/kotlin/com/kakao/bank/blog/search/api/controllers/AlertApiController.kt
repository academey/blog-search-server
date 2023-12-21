package com.kakao.bank.blog.search.api.controllers

import com.kakao.bank.blog.search.api.controllers.dto.AlertDto
import com.kakao.bank.blog.search.api.controllers.dto.ApiResponse
import com.kakao.bank.blog.search.domain.alert.AlertService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/alerts")
class AlertApiController(
    private val alertService: com.kakao.bank.blog.search.domain.alert.AlertService,
) {
    @PostMapping
    fun request(
        @RequestBody requestDto: AlertDto.RequestAlertReq,
    ): ApiResponse<Long?> {
        return ApiResponse.createSuccess(
            alertService.request(
                requestDto.target,
                requestDto.severity,
                requestDto.message,
            ),
        )
    }
}
