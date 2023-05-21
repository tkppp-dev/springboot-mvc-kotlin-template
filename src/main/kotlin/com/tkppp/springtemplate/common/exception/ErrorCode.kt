package com.tkppp.springtemplate.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val errorMessage: String) {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 에러가 발생했습니다")
}