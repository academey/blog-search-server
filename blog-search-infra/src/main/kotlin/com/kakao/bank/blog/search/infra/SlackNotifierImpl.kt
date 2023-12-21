package com.kakao.bank.blog.search.infra

import com.kakao.bank.blog.search.domain.alert.NotificationType
import com.kakao.bank.blog.search.domain.alert.Notifier
import com.kakao.bank.blog.search.domain.user.SlackUserAlertMean
import com.kakao.bank.blog.search.domain.user.UserAlertMean
import com.kakao.bank.blog.search.infra.ratelimit.RateLimit
import org.springframework.stereotype.Service

@Service
class SlackNotifierImpl : Notifier {
    @RateLimit(key = "slackExecutionLimit", capacity = 5, refillDurationSecond = 10)
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
