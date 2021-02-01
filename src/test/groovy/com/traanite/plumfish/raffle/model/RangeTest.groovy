package com.traanite.plumfish.raffle.model

import spock.lang.Specification

class RangeTest extends Specification {

    def "should create range"() {
        when:
        def range = new Range(1, 100)
        then:
        range.min == 1
        range.max == 100
    }

    def "should replace min and max when min are greater than max"() {
        when:
        def range = new Range(200, 10)
        then:
        range.min == 10
        range.max == 200
    }

}
