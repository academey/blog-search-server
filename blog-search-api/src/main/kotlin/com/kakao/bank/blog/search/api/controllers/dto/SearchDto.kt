package com.kakao.bank.blog.search.api.controllers.dto

import com.kakao.bank.blog.search.domain.blog.Blog
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
        val blogname: String,
        val thumbnail: String,
        val datetime: String,
    ) {
        companion object {
            fun of(blog: Blog) =
                BlogResponse(
                    title = blog.title,
                    contents = blog.contents,
                    url = blog.url,
                    blogname = blog.blogname,
                    thumbnail = blog.thumbnail,
                    datetime = blog.datetime,
                )
        }
    }
}
