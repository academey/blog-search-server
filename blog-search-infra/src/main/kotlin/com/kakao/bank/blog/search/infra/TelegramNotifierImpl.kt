package com.kakao.bank.blog.search.infra

import com.kakao.bank.blog.search.domain.alert.NotificationType
import com.kakao.bank.blog.search.domain.alert.Notifier
import com.kakao.bank.blog.search.domain.user.TelegramUserAlertMean
import com.kakao.bank.blog.search.domain.user.UserAlertMean
import org.springframework.stereotype.Service

@Service
class TelegramNotifierImpl : Notifier {
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
