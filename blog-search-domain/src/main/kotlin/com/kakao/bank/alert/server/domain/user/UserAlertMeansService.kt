package com.kakao.bank.alert.server.domain.user

import com.kakao.bank.alert.server.domain.alert.NotificationType
import com.kakao.bank.alert.server.utils.CustomExceptions
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserAlertMeansService(
    private val userRepository: UserRepository,
    private val userAlertMeansRepository: UserAlertMeansRepository,
) {
    fun createUserAlertMeans(
        type: NotificationType,
        userId: Long,
        token: String?,
        channelId: String?,
    ): UserAlertMean {
        return when (type) {
            NotificationType.Slack -> createSlackUserAlertMeans(userId, token!!, channelId!!)
            NotificationType.Telegram -> createTelegramUserAlertMeans(userId, token!!)
        }
    }

    fun createSlackUserAlertMeans(
        userId: Long,
        token: String,
        channelId: String,
    ): UserAlertMean {
        return userAlertMeansRepository.save(
            SlackUserAlertMean(
                user = userRepository.findByIdOrNull(userId) ?: throw CustomExceptions.NotFoundException("Not Found userId = $userId"),
                token = token,
                channelId = channelId,
            ),
        )
    }

    fun createTelegramUserAlertMeans(
        userId: Long,
        token: String,
    ): UserAlertMean {
        return userAlertMeansRepository.save(
            TelegramUserAlertMean(
                user = userRepository.findByIdOrNull(userId) ?: throw CustomExceptions.NotFoundException("Not Found userId = $userId"),
                token = token,
            ),
        )
    }
}
