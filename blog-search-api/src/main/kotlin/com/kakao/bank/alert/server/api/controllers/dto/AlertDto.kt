package com.kakao.bank.alert.server.api.controllers.dto

object AlertDto {
    data class RequestAlertReq(
        val target: List<String>,
        val severity: String,
        val message: String,
    )
}
