package com.kakao.bank.alert.server.domain.user

import org.springframework.data.repository.CrudRepository

interface UserAlertMeansRepository : CrudRepository<UserAlertMean, Long> {
    fun findByUserId(userId: Long): List<UserAlertMean>
}
