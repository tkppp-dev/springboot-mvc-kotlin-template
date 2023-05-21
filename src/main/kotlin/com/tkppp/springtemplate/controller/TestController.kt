package com.tkppp.springtemplate.controller

import com.tkppp.springtemplate.common.exception.ApiException
import com.tkppp.springtemplate.common.exception.ErrorCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/test")
class TestController {

    @GetMapping
    fun testGet(@RequestParam param1: String, @RequestParam param2: String): TestResponse {
        return TestResponse()
    }

    @GetMapping("/no-content")
    fun testNoContent(): ResponseEntity<Void> {
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/error")
    fun testError() {
        throw ApiException(ErrorCode.INTERNAL_SERVER_ERROR)
    }

    @PostMapping
    fun testPost(@RequestBody body: TestRequestBody): TestResponse {
        return TestResponse()
    }

    data class TestRequestBody(val property1: String, val property2: String)
    data class TestResponse(val message: String = "success")
}