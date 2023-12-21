package com.kakao.bank.alert.server.domain.configuration

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kakao.bank.alert.server.domain.alert.AlertService
import com.kakao.bank.alert.server.domain.message.MessageDto
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Configuration

@Configuration
class ConsumerConfiguration(
    private val alertService: AlertService,
) {
    @RabbitListener(queues = ["queue-01"])
    fun receiveMessage(message: String) {
        val objectMapper = jacksonObjectMapper()
        val messageDto: MessageDto = objectMapper.readValue(message, MessageDto::class.java)
        alertService.notify(messageDto)
    }
}
