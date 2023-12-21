package com.kakao.bank.alert.server.domain.alert

import com.kakao.bank.alert.server.domain.message.MessageDto
import com.kakao.bank.alert.server.domain.message.ProducerService
import com.kakao.bank.alert.server.domain.user.User
import com.kakao.bank.alert.server.domain.user.UserRepository
import com.kakao.bank.alert.server.utils.CustomExceptions
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AlertService(
    private val userRepository: UserRepository,
    private val alertGroupRepository: AlertGroupRepository,
    private val notifierList: List<Notifier>,
    private val producerService: ProducerService,
) {
    fun request(
        target: List<String>,
        severity: String,
        message: String,
    ): Long? =
        runBlocking {
            val map =
                target.map { keyword ->
                    when {
                        keyword == "@all" -> userRepository.findAll()
                        keyword.take(2) == "@@" -> findByAlertGroup(keyword)
                        else -> keyword.removePrefix("@").let { userRepository.findByNickname(it) }.let { listOf(it) }
                    }
                }.flatten()

            val users = map.toSet().toList()

            withContext(Dispatchers.IO) {
                users.map { user ->
                    async {
                        producerService.sendMessage(
                            MessageDto(
                                userId = user.id,
                                severity = severity,
                                message = message,
                            ),
                        )
                    }
                }.awaitAll()

                users.size.toLong()
            }
        }

    @CircuitBreaker(name = "alertService:notify", fallbackMethod = "fallback")
    fun notify(messageDto: MessageDto): Unit =
        runBlocking {
            val user = userRepository.findByIdOrNull(messageDto.userId)!!

            withContext(Dispatchers.IO) {
                user.userAlertMeanList.map { userAlertMean ->
                    async {
                        runCatching {
                            notifierList.first { it.getNotifyType() == userAlertMean.type }.execute(
                                severity = messageDto.severity,
                                message = messageDto.message,
                                userAlertMean = userAlertMean,
                            )
                        }.onFailure { e ->
                            when (e) {
                                is CallNotPermittedException -> println("서킷이 열렸습니다: $e")
                                is CustomExceptions.ApiException -> println("API 호출 실패: $e")
                                is CustomExceptions.RateLimitException -> println("Rate Limit 실패: $e")
                            }
                        }
                    }
                }.awaitAll()
            }
        }

    private fun findByAlertGroup(keyword: String): List<User> {
        val alertGroup =
            alertGroupRepository.findByGroupName(keyword.drop(2))
                ?: throw CustomExceptions.NotFoundException("Not Found alertGroupName = ${keyword.drop(2)}")
        return alertGroup.alertGroupUserList.map { it.user }
    }

    fun fallback(
        messageDto: MessageDto,
        e: Exception,
    ) {
        // TODO : dead letter 에 대한 처리 추가
        when (e) {
            is CallNotPermittedException -> println("서킷이 열렸습니다: $e")
            is CustomExceptions.ApiException -> println("API 호출 실패: $e")
            is CustomExceptions.RateLimitException -> println("Rate Limit 실패: $e")
        }
    }
}
