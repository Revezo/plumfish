package com.traanite.plumfish.raffle.infrastructure

import com.traanite.plumfish.raffle.model.Range
import spock.lang.Specification

class PseudoRandomNumberGeneratorTest extends Specification {

    PseudoRandomNumberGenerator pseudoRandomNumberGenerator = new PseudoRandomNumberGenerator()

    def "should return number from 1 to 100"() {
        given:
        def range = new Range(1, 100)
        when:
        def randomInt = pseudoRandomNumberGenerator.randomInt(range)
        then:
        randomInt.get() >= 1
        randomInt.get() <= 100
    }

    def "should return number from 101 to 200"() {
        given:
        def range = new Range(101, 200)
        when:
        def randomInt = pseudoRandomNumberGenerator.randomInt(range)
        then:
        randomInt.get() >= 101
        randomInt.get() <= 200
    }
}
