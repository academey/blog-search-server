package com.kakao.bank.alert.server.domain.alert

import com.kakao.bank.alert.server.domain.user.UserRepository
import com.kakao.bank.alert.server.utils.CustomExceptions
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AlertGroupService(
    private val userRepository: UserRepository,
    private val alertGroupRepository: AlertGroupRepository,
    private val alertGroupUserRepository: AlertGroupUserRepository,
) {
    fun create(groupName: String): AlertGroup {
        if (alertGroupRepository.findByGroupName(groupName) != null) {
            throw CustomExceptions.AlreadyExistException(
                "Already Exist groupName = $groupName",
            )
        }
        return alertGroupRepository.save(
            AlertGroup(
                groupName = groupName,
            ),
        )
    }

    fun register(
        userId: Long,
        alertGroupId: Long,
    ): AlertGroupUser {
        if (alertGroupUserRepository.findByUserIdAndAlertGroupId(
                userId = userId,
                alertGroupId = alertGroupId,
            ) != null
        ) {
            throw CustomExceptions.AlreadyExistException("Already AlertGroup Exist userId = $userId alertGroupId = $alertGroupId")
        }

        return alertGroupUserRepository.save(
            AlertGroupUser(
                user = userRepository.findByIdOrNull(userId) ?: throw CustomExceptions.NotFoundException("Not Found userId = $userId"),
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
