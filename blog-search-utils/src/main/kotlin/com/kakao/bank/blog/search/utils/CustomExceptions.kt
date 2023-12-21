package com.kakao.bank.blog.search.utils

object CustomExceptions {
    class NotFoundException(msg: String? = null) : RuntimeException(msg)

    class AlreadyExistException(msg: String? = null) : RuntimeException(msg)

    class ApiException(msg: String? = null) : RuntimeException(msg)

    class RateLimitException(msg: String? = null) : RuntimeException(msg)
}
