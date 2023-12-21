package com.kakao.bank.blog.search.domain.alert

import org.springframework.data.repository.CrudRepository

interface AlertGroupUserRepository : CrudRepository<com.kakao.bank.blog.search.domain.alert.AlertGroupUser, Long> {
    fun findByUserIdAndAlertGroupId(
        userId: Long,
        alertGroupId: Long,
    ): com.kakao.bank.blog.search.domain.alert.AlertGroupUser?
}
