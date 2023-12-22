package com.kakao.bank.blog.search.domain.search

import com.kakao.bank.blog.search.domain.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "search_result")
class SearchResult(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    @Column
    var keyword: String,
    @Column
    val sorting: Sorting,
    @Column
    val page: Int,
    @Column
    val size: Int,
) : BaseTimeEntity()
