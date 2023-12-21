package com.kakao.bank.alert.server.domain.alert

import org.springframework.data.repository.CrudRepository

interface AlertGroupRepository : CrudRepository<AlertGroup, Long> {
    fun findByGroupName(groupName: String): AlertGroup?
}
