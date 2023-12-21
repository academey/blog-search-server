package com.kakao.bank.blog.search.api.controllers.dto

import com.kakao.bank.blog.search.infra.alert.NotificationType
import com.kakao.bank.blog.search.infra.user.User
import com.kakao.bank.blog.search.infra.user.UserAlertMean
import jakarta.validation.constraints.AssertTrue

object SearchDto {
    enum class Sorting {
        accuracy,
        recency,
    }

    data class CreateUserReq(
        val nickname: String,
    )

    data class RegisterMeansReq(
        val type: NotificationType,
        val token: String?,
        val channelId: String?,
    ) {
        @get:AssertTrue(message = "type 이 Slack 일 때 token 과 channelId 는 존재해야 합니다")
        private val isTokenAndChannelIdNotEmptyWhenTypeIsSlack: Boolean
            get() {
                return if (type == NotificationType.Slack) {
                    token != null && channelId != null
                } else {
                    true
                }
            }

        @get:AssertTrue(message = "type 이 Telegram 일 때 token은 존재해야 합니다")
        private val isTokenNotEmptyWhenTypeIsTelegram: Boolean
            get() {
                return if (type == NotificationType.Telegram) {
                    token != null
                } else {
                    true
                }
            }
    }

    data class UserAlertMeanRes(
        var id: Long,
        val type: NotificationType,
    ) {
        companion object {
            fun of(userAlertMean: UserAlertMean) =
                UserAlertMeanRes(
                    id = userAlertMean.id,
                    type = userAlertMean.type,
                )
        }
    }

    data class UserRes(
        var id: Long,
        var nickname: String,
        var userAlertMeanList: List<UserAlertMeanRes>,
    ) {
        companion object {
            fun of(user: User) =
                UserRes(
                    id = user.id,
                    nickname = user.nickname,
                    userAlertMeanList = user.userAlertMeanList.map(UserAlertMeanRes.Companion::of),
                )
        }
    }
}
