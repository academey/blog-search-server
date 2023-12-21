package com.kakao.bank.blog.search.infra.ratelimit

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class RateLimit(val key: String, val capacity: Long, val refillDurationSecond: Long)
