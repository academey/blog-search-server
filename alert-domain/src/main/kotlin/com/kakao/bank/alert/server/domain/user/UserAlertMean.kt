package com.kakao.bank.alert.server.domain.user

import com.kakao.bank.alert.server.domain.BaseTimeEntity
import com.kakao.bank.alert.server.domain.alert.NotificationType
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.DiscriminatorType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "user_alert_means")
class UserAlertMean(
    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false, columnDefinition = "")
    val type: NotificationType,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        foreignKey =
            ForeignKey(
                name = "fk_user_alert_means_user",
            ),
    )
    var user: User,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
) : BaseTimeEntity()
