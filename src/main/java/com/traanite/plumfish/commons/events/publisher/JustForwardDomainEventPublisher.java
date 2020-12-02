package com.traanite.plumfish.commons.events.publisher;

import com.traanite.plumfish.commons.events.DomainEvent;
import com.traanite.plumfish.commons.events.DomainEvents;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class JustForwardDomainEventPublisher implements DomainEvents {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(DomainEvent event, String exchange, String routingKey) {
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }
}
