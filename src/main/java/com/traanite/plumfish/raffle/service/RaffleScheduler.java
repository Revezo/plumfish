package com.traanite.plumfish.raffle.service;

import com.traanite.plumfish.raffle.model.DrawPackageCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class RaffleScheduler {

    private final PackageDrawer packageDrawer;
    private final RaffleEventPublisher eventPublisher;

    // TODO delay in properties
    @Scheduled(fixedDelay = 10000)
    public void test() {
        packageDrawer.drawPackage().ifPresentOrElse(drawPackage -> eventPublisher.publish(new DrawPackageCreatedEvent(drawPackage)),
                () -> log.error("DrawPackage empty"));
    }
}
