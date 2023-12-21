package com.kakao.bank.blog.search.api.controllers

import com.kakao.bank.blog.search.api.controllers.dto.AlertGroupDto
import com.kakao.bank.blog.search.api.controllers.dto.ApiResponse
import com.kakao.bank.blog.search.domain.alert.AlertGroupService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/alert-groups")
class AlertGroupApiController(
    private val alertGroupService: com.kakao.bank.blog.search.domain.alert.AlertGroupService,
) {
    @PostMapping()
    fun create(
        @RequestBody requestDto: AlertGroupDto.CreateAlertGroupReq,
    ): ApiResponse<AlertGroupDto.AlertGroupRes> {
        return ApiResponse.createSuccess(
            alertGroupService.create(requestDto.groupName).let(AlertGroupDto.AlertGroupRes::of),
        )
    }

    @PostMapping("/{groupId}/users/{userId}")
    fun registerGroup(
        @PathVariable userId: Long,
        @PathVariable groupId: Long,
    ): ApiResponse<AlertGroupDto.AlertGroupRes> {
        return ApiResponse.createSuccess(
            alertGroupService.register(
                userId = userId,
                alertGroupId = groupId,
            ).alertGroup.let(AlertGroupDto.AlertGroupRes::of),
        )
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    fun unRegisterGroup(
        @PathVariable userId: Long,
        @PathVariable groupId: Long,
    ): ApiResponse<*> {
        alertGroupService.unRegister(
            userId = userId,
            alertGroupId = groupId,
        )

        return ApiResponse.createSuccessWithNoContent()
    }
}
