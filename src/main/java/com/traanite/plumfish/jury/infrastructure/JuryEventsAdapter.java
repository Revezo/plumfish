package com.traanite.plumfish.jury.infrastructure;

import com.traanite.plumfish.commons.events.DomainEvents;
import com.traanite.plumfish.jury.model.JuryEvents;
import com.traanite.plumfish.jury.model.MainPrizeWon;
import com.traanite.plumfish.jury.model.SecondaryPrizeWon;
import lombok.RequiredArgsConstructor;

import static com.traanite.plumfish.jury.infrastructure.JuryEventsConfig.*;

@RequiredArgsConstructor
public class JuryEventsAdapter implements JuryEvents {

    private final DomainEvents domainEvents;

    @Override
    public void publish(MainPrizeWon mainPrizeWon) {
        domainEvents.publish(mainPrizeWon, JURY_EXCHANGE_1, JURY_MAIN_PRIZE_ROUTING);
    }

    @Override
    public void publish(SecondaryPrizeWon secondaryPrizeWon) {
        domainEvents.publish(secondaryPrizeWon, JURY_EXCHANGE_1, JURY_SECONDARY_PRIZE_ROUTING);
    }
}
