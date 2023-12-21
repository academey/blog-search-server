package com.kakao.bank.alert.server.infra.ratelimit

import io.github.bucket4j.Bandwidth
import io.github.bucket4j.Bucket
import io.github.bucket4j.BucketConfiguration
import io.github.bucket4j.distributed.ExpirationAfterWriteStrategy
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager
import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.codec.ByteArrayCodec
import io.lettuce.core.codec.RedisCodec
import io.lettuce.core.codec.StringCodec
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Component
class APIRateLimiter(
    private val redisClient: RedisClient,
) {
    private lateinit var proxyManager: LettuceBasedProxyManager<String>
    private val buckets: ConcurrentMap<String, Bucket> = ConcurrentHashMap()

    init {
        val connection: StatefulRedisConnection<String, ByteArray> =
            redisClient.connect(RedisCodec.of(StringCodec.UTF8, ByteArrayCodec.INSTANCE))

        proxyManager =
            LettuceBasedProxyManager.builderFor(connection)
                .withExpirationStrategy(
                    ExpirationAfterWriteStrategy.basedOnTimeForRefillingBucketUpToMax(
                        Duration.ofSeconds(
                            100,
                        ),
                    ),
                )
                .build()
    }

    private fun getOrCreateBucket(
        apiKey: String,
        capacity: Long,
        refillDuration: Duration,
    ): Bucket {
        return buckets.computeIfAbsent(apiKey) { key: String? ->
            val configuration =
                createBucketConfiguration(
                    capacity,
                    refillDuration,
                )
            proxyManager.builder().build(key, configuration)
        }
    }

    private fun createBucketConfiguration(
        capacity: Long,
        refillDuration: Duration,
    ): BucketConfiguration {
        return BucketConfiguration.builder()
            .addLimit(
                Bandwidth.builder()
                    .capacity(capacity)
                    .refillIntervally(capacity, refillDuration)
                    .build(),
            )
            .build()
    }

    fun tryConsume(
        apiKey: String,
        capacity: Long,
        refillDurationSecond: Long,
    ): Boolean {
        val bucket = getOrCreateBucket(apiKey, capacity, Duration.ofSeconds(refillDurationSecond))
        return bucket.tryConsume(1)
    }
}
