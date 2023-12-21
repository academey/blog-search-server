package com.kakao.bank.blog.search.domain.user

import org.springframework.data.repository.CrudRepository

interface UserAlertMeansRepository : CrudRepository<UserAlertMean, Long> {
    fun findByUserId(userId: Long): List<UserAlertMean>
}
