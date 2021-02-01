package com.traanite.plumfish.raffle.application

import com.traanite.plumfish.raffle.model.*
import spock.lang.Specification

class DrawPackageSchedulerTest extends Specification {
    PackageDrawer packageDrawer = Stub()
    RaffleEvents raffleEvents = Mock()
    DrawPackageScheduler drawPackageScheduler = new DrawPackageScheduler(packageDrawer, raffleEvents)

    def "should publish raffle package drawn"() {
        given:
        def rafflePackage = new RafflePackageDrawn(new RafflePackage(1, new Star(2, 3, 4.3), new Thing("thing1")))
        packageDrawer.drawPackage() >> rafflePackage
        when:
        drawPackageScheduler.handlePackageDraw()
        then:
        1 * raffleEvents.publish(rafflePackage)
    }

    def "should publish raffle package draw failed"() {
        given:
        def rafflePackageDrawFailed = new RafflePackageDrawFailed()
        packageDrawer.drawPackage() >> rafflePackageDrawFailed
        when:
        drawPackageScheduler.handlePackageDraw()
        then:
        1 * raffleEvents.publish(rafflePackageDrawFailed)

    }
}
