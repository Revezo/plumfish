package com.traanite.plumfish.raffle.infrastructure;

import com.traanite.plumfish.commons.events.DomainEvents;
import com.traanite.plumfish.raffle.model.RaffleEvents;
import com.traanite.plumfish.raffle.model.RafflePackageDrawn;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.traanite.plumfish.raffle.infrastructure.RaffleEventsConfig.RAFFLE_EXCHANGE_1;
import static com.traanite.plumfish.raffle.infrastructure.RaffleEventsConfig.RAFFLE_NUMBER_STAR_THING_ROUTING;

@RequiredArgsConstructor
public class RaffleEventsAdapter implements RaffleEvents {
    private final DomainEvents domainEvents;

    @Override
    public void publish(@NonNull RafflePackageDrawn rafflePackageDrawn) {
        domainEvents.publish(rafflePackageDrawn, RAFFLE_EXCHANGE_1, RAFFLE_NUMBER_STAR_THING_ROUTING);

    }
}
