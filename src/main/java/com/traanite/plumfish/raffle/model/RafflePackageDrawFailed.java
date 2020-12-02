package com.traanite.plumfish.raffle.model;

import com.traanite.plumfish.commons.events.DomainEvent;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class RafflePackageDrawFailed implements DomainEvent {
    UUID eventId = UUID.randomUUID();
    Instant when = Instant.now();
}
