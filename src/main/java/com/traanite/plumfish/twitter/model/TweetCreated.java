package com.traanite.plumfish.twitter.model;

import com.traanite.plumfish.commons.events.DomainEvent;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class TweetCreated implements DomainEvent {

    UUID eventId = UUID.randomUUID();
    Instant when = Instant.now();
    @NonNull
    TwitterMessage twitterMessage;
}
