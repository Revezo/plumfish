package com.traanite.plumfish.raffle.infrastructure;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RaffleEventsConfig {

    public static final String RAFFLE_EXCHANGE_1 = "raffle.exchange.1";
    public static final String RAFFLE_QUEUE_1 = "raffle.queue.1";

    public static final String RAFFLE_NUMBER_STAR_THING_ROUTING = "number.star.thing";

    @Bean
    public TopicExchange raffleExchange() {
        return new TopicExchange(RAFFLE_EXCHANGE_1);
    }

    @Bean
    public Declarables raffleBindings(TopicExchange raffleExchange) {
        Queue queue1 = new Queue(RAFFLE_QUEUE_1, false);

        return new Declarables(
                queue1,
                BindingBuilder.bind(queue1).to(raffleExchange).with(RAFFLE_NUMBER_STAR_THING_ROUTING)
        );
    }
}
