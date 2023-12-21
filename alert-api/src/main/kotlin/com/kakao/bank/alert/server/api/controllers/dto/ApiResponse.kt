package com.kakao.bank.alert.server.api.controllers.dto

import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError

class ApiResponse<T> private constructor(
    val status: String,
    val data: T,
    val message: String?,
) {
    companion object {
        private const val SUCCESS_STATUS = "success"
        private const val FAIL_STATUS = "fail"
        private const val ERROR_STATUS = "error"

        fun <T> createSuccess(data: T): ApiResponse<T> {
            return ApiResponse(SUCCESS_STATUS, data, null)
        }

        fun createSuccessWithNoContent(): ApiResponse<*> {
            return ApiResponse<Any?>(SUCCESS_STATUS, null, null)
        }

        fun createFail(bindingResult: BindingResult): ApiResponse<*> {
            val errors: MutableMap<String, String> = HashMap()
            val allErrors = bindingResult.allErrors
            for (error in allErrors) {
                if (error is FieldError) {
                    errors[error.field] = error.getDefaultMessage().toString()
                } else {
                    errors[error.objectName] = error.defaultMessage.toString()
                }
            }
            return ApiResponse<Map<String, String>>(FAIL_STATUS, errors, null)
        }

        fun createError(message: String?): ApiResponse<*> {
            return ApiResponse<Any?>(ERROR_STATUS, null, message)
        }
    }
}
