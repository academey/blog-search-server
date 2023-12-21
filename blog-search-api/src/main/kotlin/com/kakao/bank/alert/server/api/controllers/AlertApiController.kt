package com.kakao.bank.alert.server.api.controllers

import com.kakao.bank.alert.server.api.controllers.dto.AlertDto
import com.kakao.bank.alert.server.api.controllers.dto.ApiResponse
import com.kakao.bank.alert.server.domain.alert.AlertService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/alerts")
class AlertApiController(
    private val alertService: AlertService,
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
