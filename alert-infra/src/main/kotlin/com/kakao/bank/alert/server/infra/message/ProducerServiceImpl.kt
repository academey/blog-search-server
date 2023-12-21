package com.kakao.bank.alert.server.infra.message

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kakao.bank.alert.server.domain.message.MessageDto
import com.kakao.bank.alert.server.domain.message.ProducerService
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class ProducerServiceImpl(
    private val rabbitTemplate: RabbitTemplate,
) : ProducerService {
    override fun sendMessage(messageDto: MessageDto) {
        val objectToJSON: String = jacksonObjectMapper().writeValueAsString(messageDto)
        rabbitTemplate.convertAndSend("queue-01", objectToJSON)
    }
}
