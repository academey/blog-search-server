package com.kakao.bank.alert.server.domain.message

interface ProducerService {
    fun sendMessage(messageDto: MessageDto)
}
