package com.kakao.bank.blog.search.domain.user

import com.kakao.bank.blog.search.domain.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "alert_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    @Column(unique = true)
    var nickname: String,
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    var userAlertMeanList: MutableList<UserAlertMean> = mutableListOf(),
) : com.kakao.bank.blog.search.domain.BaseTimeEntity()
