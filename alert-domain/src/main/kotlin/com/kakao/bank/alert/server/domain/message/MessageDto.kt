package com.kakao.bank.alert.server.domain.message

data class MessageDto(
    val userId: Long,
    val severity: String,
    val message: String,
)
