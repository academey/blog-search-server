package com.kakao.bank.blog.search.infra.search

import org.springframework.data.domain.Pageable

interface SearchRepository {
    fun search(
        keyword: String,
        sort: Sorting,
        pageable: Pageable,
    )

    fun getNotifyType(): SearchVendorType
}
