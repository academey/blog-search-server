package com.kakao.bank.blog.search.infra.search.naver

import com.kakao.bank.blog.search.domain.blog.Blog
import com.kakao.bank.blog.search.domain.blog.BlogVendorType

// {
// 	"lastBuildDate":"Fri, 22 Dec 2023 00:24:27 +0900",
// 	"total":1583568,
// 	"start":1,
// 	"display":10,
// 	"items":[
// 		{
// 			"title":"분산분석 ANOVA \/ T-<b>Test<\/b> 검정 정리, 차이점과 구별 방법",
// 			"link":"https:\/\/blog.naver.com\/supapa13\/223229747676",
// 			"description":"ANOVA \/ T-<b>test<\/b>는 대표적인 통계분석 방법 중 하나이다. 본 포스팅에서는 ANOVA \/ T-<b>test<\/b> 각각의 정의와 차이점, 구별 방법에 대해서 소개한다. 본문에 앞서, T-<b>Test<\/b>와 ANOVA은 전반적으로 그 방법이 비슷하다.... ",
// 			"bloggername":"세수하면이병헌 IT\/자동차",
// 			"bloggerlink":"blog.naver.com\/supapa13",
// 			"postdate":"20231007"
// 		}
// 	]
// }
data class NaverSearchBlogResult(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Item>,
) {
    data class Item(
        val title: String,
        val link: String,
        val description: String,
        val bloggername: String,
        val bloggerlink: String,
        // TODO : 추후에 datetime Parsing 하도록 변경
        val postdate: String,
    ) {
        fun toDomain() =
            Blog(
                title = title,
                contents = description,
                url = link,
                blogName = bloggername,
                datetime = postdate,
                blogVendorType = BlogVendorType.Kakao,
            )
    }

    fun getBlogs() = items.map { it.toDomain() }
}
