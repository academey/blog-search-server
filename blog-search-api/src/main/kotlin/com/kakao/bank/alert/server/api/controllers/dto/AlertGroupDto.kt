package com.kakao.bank.alert.server.api.controllers.dto

import com.kakao.bank.alert.server.domain.alert.AlertGroup

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
            fun of(alertGroup: AlertGroup) =
                AlertGroupRes(
                    id = alertGroup.id,
                    groupName = alertGroup.groupName,
                    userList = alertGroup.alertGroupUserList.map { it.user }.map(UserDto.UserRes::of),
                )
        }
    }
}
