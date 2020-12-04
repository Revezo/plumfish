package com.traanite.plumfish.jury.model;

import com.traanite.plumfish.commons.events.DomainEvent;
import com.traanite.plumfish.twitter.model.TwitterMessage;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class JuryVerdictResolver {

    public Optional<DomainEvent> giveVerdict(@NonNull TwitterMessage twitterMessage, @NonNull RandomPackage randomPackage) {
        //todo
        if (twitterMessage.getText().length() == randomPackage.getNumber() + randomPackage.getStar().getHr()) {
            log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + randomPackage);
            return Optional.of(new MainPrizeWon(twitterMessage.getCreator()));
        } else if (twitterMessage.getText().length() == 100) {
            log.info("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" + twitterMessage + " " + randomPackage);
            return Optional.of(new SecondaryPrizeWon(twitterMessage.getCreator()));
        }
        return Optional.empty();
    }
}
