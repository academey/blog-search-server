package com.kakao.bank.blog.search.domain.blog

import java.io.Serializable

data class Blog(
    val title: String,
    val contents: String,
    val url: String,
    val blogName: String,
    val datetime: String,
    val blogVendorType: BlogVendorType,
) : Serializable
