package com.kakao.bank.blog.search.api.controllers.dto

import com.kakao.bank.blog.search.domain.blog.Blog
import com.kakao.bank.blog.search.domain.blog.PopularSearchKeyword
import com.kakao.bank.blog.search.domain.search.Sorting

object SearchDto {
    enum class SortingParam {
        accuracy,
        recency,
        ;

        fun toDomain() =
            when (this) {
                accuracy -> Sorting.정확도순
                recency -> Sorting.최신순
            }
    }

    data class BlogResponse(
        val title: String,
        val contents: String,
        val url: String,
        val blogName: String,
        val datetime: String,
    ) {
        companion object {
            fun of(blog: Blog) =
                BlogResponse(
                    title = blog.title,
                    contents = blog.contents,
                    url = blog.url,
                    blogName = blog.blogName,
                    datetime = blog.datetime,
                )
        }
    }

    data class PopularSearchKeywordResponse(
        val keyword: String,
        val count: Int,
    ) {
        companion object {
            fun of(popularSearchKeyword: PopularSearchKeyword) =
                PopularSearchKeywordResponse(
                    keyword = popularSearchKeyword.keyword,
                    count = popularSearchKeyword.count,
                )
        }
    }
}
