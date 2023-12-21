package com.kakao.bank.blog.search.domain.message

data class MessageDto(
    val userId: Long,
    val severity: String,
    val message: String,
)
