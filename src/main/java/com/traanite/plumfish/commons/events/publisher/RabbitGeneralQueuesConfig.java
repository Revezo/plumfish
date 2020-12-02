package com.traanite.plumfish.commons.events.publisher;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitGeneralQueuesConfig {

    public static final String ERROR_EXCHANGE_1 = "error.exchange.1";
    public static final String ERROR_QUEUE_1 = "error.queue.1";

    @Bean
    public FanoutExchange errorExchange() {
        return new FanoutExchange(ERROR_EXCHANGE_1);
    }

    @Bean
    public Declarables errorBindings(FanoutExchange errorExchange) {
        Queue queue1 = new Queue(ERROR_QUEUE_1, false);

        return new Declarables(
                queue1,
                BindingBuilder.bind(queue1).to(errorExchange)
        );
    }
}
