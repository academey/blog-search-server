package com.kakao.bank.blog.search.api.configuration

import com.kakao.bank.blog.search.api.controllers.dto.ApiResponse
import com.kakao.bank.blog.search.utils.CustomExceptions
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice() {
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected fun defaultException(
        request: HttpServletRequest,
        e: Exception,
    ): ApiResponse<*> {
        e.printStackTrace()
        return ApiResponse.createError(e.message ?: "서버 장애가 발생했습니다")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(bindingResult: BindingResult?): ResponseEntity<ApiResponse<*>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ApiResponse.createFail(
                bindingResult!!,
            ),
        )
    }

    @ExceptionHandler(CustomExceptions.ApiException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun apiException(
        request: HttpServletRequest,
        e: CustomExceptions.ApiException,
    ): ApiResponse<*> {
        return ApiResponse.createError(e.message ?: "서버 장애가 발생했습니다")
    }

    @ExceptionHandler(CustomExceptions.NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundException(
        request: HttpServletRequest,
        e: CustomExceptions.NotFoundException,
    ): ApiResponse<*> {
        return ApiResponse.createError(e.message ?: "해당 리소스를 발견하지 못했습니다")
    }

    @ExceptionHandler(CustomExceptions.AlreadyExistException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun alreadyExistException(
        request: HttpServletRequest,
        e: CustomExceptions.AlreadyExistException,
    ): ApiResponse<*> {
        return ApiResponse.createError(e.message ?: "해당 리소스를 발견하지 못했습니다")
    }
}
