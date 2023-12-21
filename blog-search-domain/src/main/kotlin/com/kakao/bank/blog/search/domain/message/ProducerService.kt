package com.kakao.bank.blog.search.domain.message

interface ProducerService {
    fun sendMessage(messageDto: MessageDto)
}
