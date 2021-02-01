package com.traanite.plumfish.jury.application

import com.traanite.plumfish.jury.model.PackageRepository
import com.traanite.plumfish.jury.model.RandomPackage
import com.traanite.plumfish.raffle.model.RafflePackage
import com.traanite.plumfish.raffle.model.RafflePackageDrawn
import com.traanite.plumfish.raffle.model.Star
import com.traanite.plumfish.raffle.model.Thing
import spock.lang.Specification

class RaffleEventsHandlerTest extends Specification {

    PackageRepository packageRepository = Mock()
    RaffleEventsHandler raffleEventsHandler = new RaffleEventsHandler(packageRepository)

    def "should update last package after receiving new one"() {
        given:
        def rafflePackage = new RafflePackage(2, new Star(3, 4, 5.1), new Thing("thing1"))
        def rafflePackageDrawn = new RafflePackageDrawn(rafflePackage)
        when:
        raffleEventsHandler.receiveMessage(rafflePackageDrawn)
        then:
        1 * packageRepository.updateLastPackage(new RandomPackage(rafflePackageDrawn.getRafflePackage().getNumber(),
                rafflePackageDrawn.getRafflePackage().getStar(), rafflePackageDrawn.getRafflePackage().getThing()))
    }
}
