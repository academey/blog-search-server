package com.kakao.bank.blog.search.domain.alert

import com.kakao.bank.blog.search.domain.user.UserRepository
import com.kakao.bank.blog.search.utils.CustomExceptions
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AlertGroupService(
    private val userRepository: UserRepository,
    private val alertGroupRepository: com.kakao.bank.blog.search.domain.alert.AlertGroupRepository,
    private val alertGroupUserRepository: com.kakao.bank.blog.search.domain.alert.AlertGroupUserRepository,
) {
    fun create(groupName: String): com.kakao.bank.blog.search.domain.alert.AlertGroup {
        if (alertGroupRepository.findByGroupName(groupName) != null) {
            throw CustomExceptions.AlreadyExistException(
                "Already Exist groupName = $groupName",
            )
        }
        return alertGroupRepository.save(
            com.kakao.bank.blog.search.domain.alert.AlertGroup(
                groupName = groupName,
            ),
        )
    }

    fun register(
        userId: Long,
        alertGroupId: Long,
    ): com.kakao.bank.blog.search.domain.alert.AlertGroupUser {
        if (alertGroupUserRepository.findByUserIdAndAlertGroupId(
                userId = userId,
                alertGroupId = alertGroupId,
            ) != null
        ) {
            throw CustomExceptions.AlreadyExistException("Already AlertGroup Exist userId = $userId alertGroupId = $alertGroupId")
        }

        return alertGroupUserRepository.save(
            com.kakao.bank.blog.search.domain.alert.AlertGroupUser(
                user =
                    userRepository.findByIdOrNull(userId)
                        ?: throw CustomExceptions.NotFoundException("Not Found userId = $userId"),
                alertGroup =
                    alertGroupRepository.findByIdOrNull(alertGroupId)
                        ?: throw CustomExceptions.NotFoundException("Not Found alertGroupId = $alertGroupId"),
            ),
        )
    }

    fun unRegister(
        userId: Long,
        alertGroupId: Long,
    ) {
        val alertGroupUser =
            alertGroupUserRepository.findByUserIdAndAlertGroupId(
                userId = userId,
                alertGroupId = alertGroupId,
            ) ?: throw CustomExceptions.NotFoundException("Not Found userId = $userId alertGroupId = $alertGroupId,")

        alertGroupUserRepository.delete(alertGroupUser)
    }
}
