package com.kakao.bank.blog.search.domain.alert

import org.springframework.data.repository.CrudRepository

interface AlertGroupRepository : CrudRepository<com.kakao.bank.blog.search.domain.alert.AlertGroup, Long> {
    fun findByGroupName(groupName: String): com.kakao.bank.blog.search.domain.alert.AlertGroup?
}
