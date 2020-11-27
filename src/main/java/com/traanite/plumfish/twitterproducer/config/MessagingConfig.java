package com.traanite.plumfish.twitterproducer.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfig {

    // TODO to properties, queue names injectable
    public static final String FANOUT_EXCHANGE = "twitter.exchange";
    public static final String TWITTER_QUEUE_1 = "twitter.queue.1";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Declarables fanoutBindings(FanoutExchange fanoutExchange) {
        Queue queue1 = new Queue(TWITTER_QUEUE_1, false);

        return new Declarables(
                queue1,
                BindingBuilder.bind(queue1).to(fanoutExchange)
        );
    }
}
