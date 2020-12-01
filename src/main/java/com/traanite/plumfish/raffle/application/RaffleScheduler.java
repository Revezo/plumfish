package com.traanite.plumfish.raffle.application;

import com.traanite.plumfish.raffle.model.RaffleEvents;
import com.traanite.plumfish.raffle.model.RafflePackageDrawn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class RaffleScheduler {

    private final PackageDrawer packageDrawer;
    private final RaffleEvents raffleEvents;

    // TODO delay in properties
    @Scheduled(fixedDelay = 100000)
    public void test() {
        packageDrawer.drawPackage().ifPresentOrElse(drawPackage -> raffleEvents.publish(new RafflePackageDrawn(drawPackage)),
                () -> log.error("DrawPackage empty"));
    }
}
