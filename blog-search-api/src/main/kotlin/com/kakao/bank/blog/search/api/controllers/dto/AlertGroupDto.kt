package com.kakao.bank.blog.search.api.controllers.dto

import com.kakao.bank.blog.search.domain.alert.AlertGroup

object AlertGroupDto {
    data class CreateAlertGroupReq(
        val groupName: String,
    )

    data class AlertGroupRes(
        var id: Long,
        val groupName: String,
        var userList: List<UserDto.UserRes>,
    ) {
        companion object {
            fun of(alertGroup: com.kakao.bank.blog.search.domain.alert.AlertGroup) =
                AlertGroupRes(
                    id = alertGroup.id,
                    groupName = alertGroup.groupName,
                    userList = alertGroup.alertGroupUserList.map { it.user }.map(UserDto.UserRes.Companion::of),
                )
        }
    }
}
