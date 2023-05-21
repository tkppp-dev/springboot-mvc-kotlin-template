package com.tkppp.springtemplate.common.exception

import com.tkppp.springtemplate.common.util.getLogger
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandlerAdvice {

    private val logger: Logger = getLogger()

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException): ResponseEntity<ErrorResponse> {
        logger.info("${e.errorCode}", e)
        return ResponseEntity.status(e.errorCode.status)
            .body(ErrorResponse(e))
    }

    @ExceptionHandler(Exception::class)
    fun handlerException(e: Exception): ResponseEntity<ErrorResponse> {
        logger.info("${e.message}", e)
        return ResponseEntity.internalServerError()
            .body(ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR))
    }

    data class ErrorResponse(val status: HttpStatus, val errorMessage: String) {
        constructor(errorCode: ErrorCode) : this(errorCode.status, errorCode.errorMessage)
        constructor(e: ApiException) : this(e.errorCode.status, e.errorCode.errorMessage)
    }
}