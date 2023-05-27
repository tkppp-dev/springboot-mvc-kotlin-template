package com.tkppp.springtemplate.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*

@AutoConfigureMockMvc
@WebMvcTest
class TestControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc
    @Autowired
    private ObjectMapper objectMapper

    def "simple get test"() {
        given:
        def response = [message: "success", obj: [param1: "a", param2: "b"]]

        when:
        def result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("param1", "a")
                        .param("param2", "b"))
                .andDo(print()).andReturn().response

        then:
        result.status == 200
        result.contentAsString == objectMapper.writeValueAsString(response)
    }

    def "simple get error test"() {
        when:
        def result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/test/error"))
                .andDo(print()).andReturn().response

        then:
        println(result.contentAsString)
        result.status == 500
    }

    def "simple post test"() {
        given:
        def requestBody = new TestController.TestRequestBody("a", "b")
        def response = [message: "success", obj: [property1: "a", property2: "b"]]

        when:
        def result = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andDo(print()).andReturn().response

        then:
        result.status == 200
        result.contentAsString == objectMapper.writeValueAsString(response)
    }
}
