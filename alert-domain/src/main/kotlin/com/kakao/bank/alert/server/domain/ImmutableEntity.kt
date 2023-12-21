package com.kakao.bank.alert.server.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class ImmutableEntity(
    @CreatedDate
    @Column(nullable = false)
    @JsonIgnore
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
)
