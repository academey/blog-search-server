package com.kakao.bank.blog.search.domain.alert

import com.kakao.bank.blog.search.domain.user.UserAlertMean

interface Notifier {
    fun execute(
        severity: String,
        message: String,
        userAlertMean: UserAlertMean,
    )

    fun getNotifyType(): NotificationType
}
