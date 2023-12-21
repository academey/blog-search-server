package com.kakao.bank.blog.search.infra.search

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val searchRepositories: List<SearchRepository>,
) {
    fun search(
        keyword: String,
        sort: Sorting,
        pageable: Pageable,
    ): Unit {

//        if (userRepository.findByNickname(nickname) != null) {
//            throw CustomExceptions.AlreadyExistException(
//                "Already Exist nickname = $nickname",
//            )
//        }
//        return userRepository.save(
//            User(
//                nickname = nickname,
//            ),
//        )
    }
}
