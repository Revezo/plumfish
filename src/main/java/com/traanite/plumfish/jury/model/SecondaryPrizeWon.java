package com.traanite.plumfish.jury.model;

import com.traanite.plumfish.commons.events.DomainEvent;
import com.traanite.plumfish.twitter.model.TwitterUser;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class SecondaryPrizeWon implements DomainEvent {
    UUID eventId = UUID.randomUUID();
    Instant when = Instant.now();
    @NonNull
    TwitterUser twitterUser;
}
