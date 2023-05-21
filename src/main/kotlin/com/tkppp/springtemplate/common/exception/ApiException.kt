package com.tkppp.springtemplate.common.exception

class ApiException(val errorCode: ErrorCode) : RuntimeException()