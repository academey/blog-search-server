package com.kakao.bank.blog.search.api.controllers.dto

import com.kakao.bank.blog.search.domain.search.Sorting

object SearchDto {
    enum class SortingParam {
        accuracy,
        recency;

        fun toDomain() = when(this) {
            accuracy -> Sorting.정확도순
            recency -> Sorting.최신순
        }
    }
}
