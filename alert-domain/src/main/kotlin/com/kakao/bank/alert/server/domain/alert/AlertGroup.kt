package com.kakao.bank.alert.server.domain.alert

import com.kakao.bank.alert.server.domain.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "alert_group")
class AlertGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    @Column(unique = true)
    val groupName: String,
    @OneToMany(mappedBy = "alertGroup", fetch = FetchType.LAZY)
    var alertGroupUserList: MutableList<AlertGroupUser> = mutableListOf(),
) : BaseTimeEntity()
