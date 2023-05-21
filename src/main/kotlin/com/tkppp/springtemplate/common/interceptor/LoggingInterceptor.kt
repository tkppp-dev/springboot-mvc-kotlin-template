package com.tkppp.springtemplate.common.interceptor

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.tkppp.springtemplate.common.util.getLogger
import org.slf4j.Logger
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.lang.Exception
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoggingInterceptor(private val objectMapper: ObjectMapper) : HandlerInterceptor {

    companion object {
        const val REQUEST_START_TIME = "request_start_time"
    }

    val logger: Logger = getLogger()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.setAttribute(REQUEST_START_TIME, LocalDateTime.now())
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        val startTime = request.getAttribute(REQUEST_START_TIME) as LocalDateTime
        apiLogging(startTime, request as ContentCachingRequestWrapper, response as ContentCachingResponseWrapper)
    }

    private fun apiLogging(
        startTime: LocalDateTime,
        request: ContentCachingRequestWrapper,
        response: ContentCachingResponseWrapper
    ) {
        logger.info(
            """
            
            | Started ${request.method} ${request.requestURI} for ${request.remoteAddr} at $startTime
            | Headers: ${getHeaders(request)}
            | Params: ${getParams(request)}
            | Request Body: ${getBody(request.contentAsByteArray)}
            | Response Body: ${getBody(response.contentAsByteArray)}
            | Completed ${response.status} in ${ChronoUnit.MILLIS.between(startTime, LocalDateTime.now())}ms
        """.trimIndent()
        )
    }

    private fun getHeaders(request: ContentCachingRequestWrapper): Map<String, String> {
        val map = hashMapOf<String, String>()

        for (key in request.headerNames) {
            map[key] = request.getHeader(key)
        }

        return map
    }

    private fun getParams(request: ContentCachingRequestWrapper): Map<String, String> {
        val map = hashMapOf<String, String>()

        for (key in request.parameterNames) {
            println("$key ${request.getParameter(key)}")
            map[key] = request.getParameter(key)
        }

        return map
    }

    private fun getBody(content: ByteArray): JsonNode {
        return if (content.isEmpty()) {
            objectMapper.createObjectNode()
        } else {
            objectMapper.readTree(content)
        }
    }
}