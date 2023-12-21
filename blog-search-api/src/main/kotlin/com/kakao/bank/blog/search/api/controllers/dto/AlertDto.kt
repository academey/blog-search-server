package com.kakao.bank.blog.search.api.controllers.dto

object AlertDto {
    data class RequestAlertReq(
        val target: List<String>,
        val severity: String,
        val message: String,
    )
}
