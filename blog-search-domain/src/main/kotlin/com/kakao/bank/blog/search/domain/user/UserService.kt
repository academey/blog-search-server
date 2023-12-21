package com.kakao.bank.blog.search.domain.user

import com.kakao.bank.blog.search.utils.CustomExceptions
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun create(nickname: String): User {
        if (userRepository.findByNickname(nickname) != null) {
            throw CustomExceptions.AlreadyExistException(
                "Already Exist nickname = $nickname",
            )
        }
        return userRepository.save(
            User(
                nickname = nickname,
            ),
        )
    }
}
