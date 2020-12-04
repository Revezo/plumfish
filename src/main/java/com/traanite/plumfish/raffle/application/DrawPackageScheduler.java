package com.traanite.plumfish.raffle.application;

import com.traanite.plumfish.commons.events.DomainEvent;
import com.traanite.plumfish.raffle.model.PackageDrawer;
import com.traanite.plumfish.raffle.model.RaffleEvents;
import com.traanite.plumfish.raffle.model.RafflePackageDrawFailed;
import com.traanite.plumfish.raffle.model.RafflePackageDrawn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class DrawPackageScheduler {

    private final PackageDrawer packageDrawer;
    private final RaffleEvents raffleEvents;

    @Scheduled(fixedDelayString = "${modules.raffle.drawPackageScheduler.delay}")
    public void handlePackageDraw() {
        DomainEvent drawPackageResult = packageDrawer.drawPackage();
        // TODO such instance checks can be quite nicely replaced with vavr.io
        if (drawPackageResult instanceof RafflePackageDrawn) {
            raffleEvents.publish((RafflePackageDrawn) drawPackageResult);
        } else if (drawPackageResult instanceof RafflePackageDrawFailed) {
            raffleEvents.publish((RafflePackageDrawFailed) drawPackageResult);
        }
    }
}
