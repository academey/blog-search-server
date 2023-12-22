package com.kakao.bank.blog.search.domain.search

import com.kakao.bank.blog.search.domain.blog.Blog
import com.kakao.bank.blog.search.utils.JsonUtils
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class BlogListConverter : AttributeConverter<List<Blog>, String> {
    override fun convertToDatabaseColumn(attribute: List<Blog>): String = JsonUtils.nullSafeToJson(attribute)

    override fun convertToEntityAttribute(dbData: String) = JsonUtils.fromJson<List<Blog>>(dbData)
}
