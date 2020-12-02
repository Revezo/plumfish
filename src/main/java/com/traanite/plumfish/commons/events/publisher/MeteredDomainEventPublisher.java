package com.traanite.plumfish.commons.events.publisher;

import com.traanite.plumfish.commons.events.DomainEvent;
import com.traanite.plumfish.commons.events.DomainEvents;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class MeteredDomainEventPublisher implements DomainEvents {

    private static final String DOMAIN_EVENTS = "domain_events";
    private static final String TAG_NAME = "name";

    private final DomainEvents delegate;
    private final MeterRegistry metricsRegistry;

    @Override
    public void publish(DomainEvent event, String exchange, String routingKey) {
        delegate.publish(event, exchange, routingKey);
        metricsRegistry.counter(DOMAIN_EVENTS, TAG_NAME, event.getClass().getSimpleName()).increment();
    }
}
