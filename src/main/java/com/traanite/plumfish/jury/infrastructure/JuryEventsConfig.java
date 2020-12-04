package com.traanite.plumfish.jury.infrastructure;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JuryEventsConfig {

    public static final String JURY_EXCHANGE_1 = "jury.exchange.1";
    public static final String JURY_MAIN_PRIZE_QUEUE = "jury.main.prize.queue";
    public static final String JURY_SECONDARY_PRIZE_QUEUE = "jury.secondary.prize.queue";

    public static final String JURY_MAIN_PRIZE_ROUTING = "main.prize";
    public static final String JURY_SECONDARY_PRIZE_ROUTING = "secondary.prize";

    @Bean
    public DirectExchange juryExchange() {
        return new DirectExchange(JURY_EXCHANGE_1);
    }

    @Bean
    public Declarables juryBindings(DirectExchange juryExchange) {
        Queue queue1 = new Queue(JURY_MAIN_PRIZE_QUEUE, false);
        Queue queue2 = new Queue(JURY_SECONDARY_PRIZE_QUEUE, false);

        return new Declarables(
                queue1, queue2,
                BindingBuilder.bind(queue1).to(juryExchange).with(JURY_MAIN_PRIZE_ROUTING),
                BindingBuilder.bind(queue2).to(juryExchange).with(JURY_SECONDARY_PRIZE_ROUTING)
        );
    }
}
