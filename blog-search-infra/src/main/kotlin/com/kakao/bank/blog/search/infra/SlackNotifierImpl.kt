package com.kakao.bank.blog.search.infra

import com.kakao.bank.blog.search.domain.alert.NotificationType
import com.kakao.bank.blog.search.domain.alert.Notifier
import com.kakao.bank.blog.search.domain.user.SlackUserAlertMean
import com.kakao.bank.blog.search.domain.user.UserAlertMean
import org.springframework.stereotype.Service

@Service
class SlackNotifierImpl : Notifier {
    override fun execute(
        severity: String,
        message: String,
        userAlertMean: UserAlertMean,
    ) {
        val slackUserAlertMean = userAlertMean as SlackUserAlertMean

        println("slack notify $severity $message ${slackUserAlertMean.channelId} ${slackUserAlertMean.token}")
    }

    override fun getNotifyType(): NotificationType {
        return NotificationType.Slack
    }
}
