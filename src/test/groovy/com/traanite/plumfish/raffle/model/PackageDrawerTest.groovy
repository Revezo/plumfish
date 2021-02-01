package com.traanite.plumfish.raffle.model


import com.traanite.plumfish.raffle.infrastructure.PseudoRandomNumberGenerator
import spock.lang.Specification

class PackageDrawerTest extends Specification {
    Star star0 = new Star(0, 0, 0)
    Star star1 = new Star(1, 1, 1)
    Star star2 = new Star(2, 2, 2)
    Stars stars = new Stars(Arrays.asList(star0, star1, star2))
    Things things = new Things(Arrays.asList("thing0", "thing1", "thing2"))
    PseudoRandomNumberGenerator numberGenerator = Mock()

    def "should draw a correct package"() {
        given:
        def drawer = new PackageDrawer(things, stars, numberGenerator)
        def randomNumber = 22
        def randomStarNumber = 0
        def randomThingNumber = 1
        numberGenerator.randomInt(new Range(1, 10000)) >> Optional.of(randomNumber)
        numberGenerator.randomInt(new Range(1, stars.getData().size())) >>> [Optional.of(randomStarNumber), Optional.of(randomThingNumber)]
        when:
        def drawPackageResult = drawer.drawPackage() as RafflePackageDrawn
        then:
        drawPackageResult.rafflePackage.number == randomNumber
        drawPackageResult.rafflePackage.star == stars.data.get(randomStarNumber)
        drawPackageResult.rafflePackage.thing.name == things.names.get(randomThingNumber)
    }

    def "should return draw package failed"() {
        given:
        def drawer = new PackageDrawer(things, stars, numberGenerator)
        numberGenerator.randomInt(new Range(1, 10000)) >> Optional.ofNullable(null)
        numberGenerator.randomInt(new Range(1, stars.getData().size())) >>> [Optional.of(1), Optional.of(2)]
        when:
        def drawPackageResult = drawer.drawPackage()
        then:
        drawPackageResult instanceof RafflePackageDrawFailed
    }
}
