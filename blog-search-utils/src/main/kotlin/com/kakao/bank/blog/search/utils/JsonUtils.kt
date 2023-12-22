package com.kakao.bank.blog.search.utils

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

object JsonUtils {
    val objectMapper =
        jacksonObjectMapper()
            .also {
                it.propertyNamingStrategy = PropertyNamingStrategies.SnakeCaseStrategy()
                it.findAndRegisterModules()
            }

    fun <T : Any> toMap(obj: T): Map<String, *> = objectMapper.convertValue(obj)

    fun <T> toJson(obj: T?): String? = obj?.let(objectMapper::writeValueAsString)

    fun <T> nullSafeToJson(obj: T): String = obj.let(objectMapper::writeValueAsString)

    inline fun <reified T> fromJson(json: String) = objectMapper.readValue<T>(json)
}
