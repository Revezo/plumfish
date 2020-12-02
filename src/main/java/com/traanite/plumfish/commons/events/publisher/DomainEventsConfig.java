package com.traanite.plumfish.commons.events.publisher;

import com.traanite.plumfish.commons.events.DomainEvents;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainEventsConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    DomainEvents domainEvents(RabbitTemplate rabbitTemplate, MeterRegistry meterRegistry) {
        return new MeteredDomainEventPublisher(new JustForwardDomainEventPublisher(rabbitTemplate), meterRegistry);
    }
}
