package com.kakao.bank.alert.server.domain.alert

import com.kakao.bank.alert.server.domain.user.UserAlertMean

interface Notifier {
    fun execute(
        severity: String,
        message: String,
        userAlertMean: UserAlertMean,
    )

    fun getNotifyType(): NotificationType
}
