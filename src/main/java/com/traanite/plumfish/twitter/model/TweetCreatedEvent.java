package com.traanite.plumfish.twitter.model;

import com.traanite.plumfish.commons.events.DomainEvent;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class TweetCreatedEvent implements DomainEvent {

    @NonNull
    UUID eventId = UUID.randomUUID();
    @NonNull
    Instant when = Instant.now();
    @NonNull
    TwitterMessage twitterMessage;


}
