package com.kakao.bank.alert.server.domain.alert

import org.springframework.data.repository.CrudRepository

interface AlertGroupUserRepository : CrudRepository<AlertGroupUser, Long> {
    fun findByUserIdAndAlertGroupId(
        userId: Long,
        alertGroupId: Long,
    ): AlertGroupUser?
}
