package com.traanite.plumfish.twitter.infrastructure;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterEventsConfig {

    public static final String TWITTER_EXCHANGE_1 = "twitter.exchange.1";
    public static final String TWITTER_QUEUE_1 = "twitter.queue.1";

    @Bean
    public FanoutExchange twitterExchange() {
        return new FanoutExchange(TWITTER_EXCHANGE_1);
    }

    @Bean
    public Declarables twitterBindings(FanoutExchange fanoutExchange) {
        Queue queue1 = new Queue(TWITTER_QUEUE_1, false);

        return new Declarables(
                queue1,
                BindingBuilder.bind(queue1).to(fanoutExchange)
        );
    }
}
