package com.traanite.plumfish.twitter.service;

import com.traanite.plumfish.twitter.model.TweetCreatedEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.traanite.plumfish.twitter.config.TwitterEventConfig.TWITTER_EXCHANGE_1;

@RequiredArgsConstructor
public class TwitterEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(@NonNull TweetCreatedEvent tweetCreatedEvent) {
        rabbitTemplate.convertAndSend(TWITTER_EXCHANGE_1, "", tweetCreatedEvent);
    }

}
