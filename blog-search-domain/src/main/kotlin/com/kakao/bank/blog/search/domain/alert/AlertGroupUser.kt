package com.kakao.bank.blog.search.domain.alert

import com.kakao.bank.blog.search.domain.BaseTimeEntity
import com.kakao.bank.blog.search.domain.user.User
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "alert_group_user_mapping")
class AlertGroupUser(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        foreignKey =
            ForeignKey(
                name = "fk_alert_group_user_mapping_user",
            ),
    )
    var user: User,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "alert_group_id",
        nullable = false,
        foreignKey =
            ForeignKey(
                name = "fk_alert_group",
            ),
    )
    var alertGroup: com.kakao.bank.blog.search.domain.alert.AlertGroup,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
) : com.kakao.bank.blog.search.domain.BaseTimeEntity()