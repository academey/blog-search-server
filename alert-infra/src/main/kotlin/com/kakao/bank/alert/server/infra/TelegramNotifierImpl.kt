package com.kakao.bank.alert.server.infra

import com.kakao.bank.alert.server.domain.alert.NotificationType
import com.kakao.bank.alert.server.domain.alert.Notifier
import com.kakao.bank.alert.server.domain.user.TelegramUserAlertMean
import com.kakao.bank.alert.server.domain.user.UserAlertMean
import com.kakao.bank.alert.server.infra.ratelimit.RateLimit
import org.springframework.stereotype.Service

@Service
class TelegramNotifierImpl : Notifier {
    @RateLimit(key = "telegramExecutionLimit", capacity = 2, refillDurationSecond = 10)
    override fun execute(
        severity: String,
        message: String,
        userAlertMean: UserAlertMean,
    ) {
        val telegramUserAlertMean = userAlertMean as TelegramUserAlertMean

        println("telegram notify $severity $message ${telegramUserAlertMean.token}")
    }

    override fun getNotifyType(): NotificationType {
        return NotificationType.Telegram
    }
}
