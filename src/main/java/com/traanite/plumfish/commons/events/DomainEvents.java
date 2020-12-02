package com.traanite.plumfish.commons.events;

public interface DomainEvents {

    void publish(DomainEvent domainEvent, String exchange, String routingKey);
}
