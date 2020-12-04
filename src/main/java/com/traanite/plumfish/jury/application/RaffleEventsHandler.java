package com.traanite.plumfish.jury.application;

import com.traanite.plumfish.jury.model.PackageRepository;
import com.traanite.plumfish.jury.model.RandomPackage;
import com.traanite.plumfish.raffle.model.RafflePackageDrawn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import static com.traanite.plumfish.raffle.infrastructure.RaffleEventsConfig.RAFFLE_QUEUE_1;

@Slf4j
@RequiredArgsConstructor
public class RaffleEventsHandler {

    private final PackageRepository packageRepository;

    @RabbitListener(queues = RAFFLE_QUEUE_1)
    public void receiveMessage(final RafflePackageDrawn rafflePackageDrawn) {
        log.info("RECEIVED: {}", rafflePackageDrawn);
        packageRepository.updateLastPackage(new RandomPackage(rafflePackageDrawn.getRafflePackage().getNumber(),
                rafflePackageDrawn.getRafflePackage().getStar(), rafflePackageDrawn.getRafflePackage().getThing()));
    }

}
