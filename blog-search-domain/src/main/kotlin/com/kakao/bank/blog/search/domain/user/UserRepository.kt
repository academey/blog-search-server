package com.kakao.bank.blog.search.domain.user

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByNickname(nickname: String): User?
}
