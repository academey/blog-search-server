package com.kakao.bank.blog.search.infra.ratelimit

import com.kakao.bank.blog.search.utils.CustomExceptions
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@Component
class RateLimitingAspect
    @Autowired
    constructor(private val apiRateLimiter: APIRateLimiter) {
        @Around("@annotation(rateLimit)")
        fun rateLimit(
            joinPoint: ProceedingJoinPoint,
            rateLimit: RateLimit,
        ): Any? {
            return if (apiRateLimiter.tryConsume(rateLimit.key, rateLimit.capacity, rateLimit.refillDurationSecond)) {
                joinPoint.proceed()
            } else {
                throw CustomExceptions.RateLimitException("Rate limit exceeded for key: ${rateLimit.key}")
            }
        }
    }
