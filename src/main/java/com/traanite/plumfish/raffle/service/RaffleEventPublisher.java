package com.traanite.plumfish.raffle.service;

import com.traanite.plumfish.raffle.model.DrawPackageCreatedEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.traanite.plumfish.raffle.config.RaffleEventConfig.RAFFLE_EXCHANGE_1;
import static com.traanite.plumfish.raffle.config.RaffleEventConfig.RAFFLE_NUMBER_STAR_THING_ROUTING;

@RequiredArgsConstructor
public class RaffleEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(@NonNull DrawPackageCreatedEvent drawPackageCreatedEvent) {
        rabbitTemplate.convertAndSend(RAFFLE_EXCHANGE_1, RAFFLE_NUMBER_STAR_THING_ROUTING, drawPackageCreatedEvent);
    }

}
