package com.tkppp.springtemplate.controller

import com.tkppp.springtemplate.common.exception.ApiException
import com.tkppp.springtemplate.common.exception.ErrorCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/test")
class TestController {

    @GetMapping
    fun testGet(@ModelAttribute parameters: TestParameters): TestResponse {
        return TestResponse(obj = parameters)
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
        return TestResponse(obj = body)
    }

    data class TestParameters(val param1: String, val param2: String)
    data class TestRequestBody(val property1: String, val property2: String)
    data class TestResponse(val message: String = "success", val obj: Any? = null)
}