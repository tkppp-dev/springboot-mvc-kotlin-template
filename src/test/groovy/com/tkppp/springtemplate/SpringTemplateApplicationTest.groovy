package com.tkppp.springtemplate

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SpringTemplateApplicationTest extends Specification {

    def "simple test"() {
        when:
        println("test execute")

        then:
        noExceptionThrown()
    }
}
