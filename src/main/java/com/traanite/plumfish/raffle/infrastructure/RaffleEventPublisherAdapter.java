package com.traanite.plumfish.raffle.infrastructure;

import com.traanite.plumfish.raffle.model.RaffleEvents;
import com.traanite.plumfish.raffle.model.RafflePackageDrawn;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.traanite.plumfish.raffle.infrastructure.RaffleEventConfig.RAFFLE_EXCHANGE_1;
import static com.traanite.plumfish.raffle.infrastructure.RaffleEventConfig.RAFFLE_NUMBER_STAR_THING_ROUTING;

@RequiredArgsConstructor
public class RaffleEventPublisherAdapter implements RaffleEvents {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(@NonNull RafflePackageDrawn rafflePackageDrawn) {
        rabbitTemplate.convertAndSend(RAFFLE_EXCHANGE_1, RAFFLE_NUMBER_STAR_THING_ROUTING, rafflePackageDrawn);

    }
}
