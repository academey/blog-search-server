package com.kakao.bank.blog.search.domain.blog

data class Blog(
    val title: String,
    val contents: String,
    val url: String,
    val blogName: String,
    val datetime: String,
    val blogVendorType: BlogVendorType,
)
