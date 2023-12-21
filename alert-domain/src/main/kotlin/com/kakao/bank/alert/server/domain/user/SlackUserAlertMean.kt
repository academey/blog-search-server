package com.kakao.bank.alert.server.domain.user

import com.kakao.bank.alert.server.domain.alert.NotificationType
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
// TODO : 해당 값을 하드코딩 하지 않고 Compile 타임에 관리할 수 있나?
@DiscriminatorValue(value = "Slack")
class SlackUserAlertMean(
    user: User,
    val token: String,
    val channelId: String,
) : UserAlertMean(NotificationType.Slack, user)
