package com.kakao.bank.blog.search.domain.blog

data class Blog(
    val title: String,
    val contents: String,
    val url: String,
    val blogname: String,
    val thumbnail: String,
    val datetime: String,
    val blogVendorType: BlogVendorType,
)
