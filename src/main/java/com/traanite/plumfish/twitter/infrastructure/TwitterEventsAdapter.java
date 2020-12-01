package com.traanite.plumfish.twitter.infrastructure;

import com.traanite.plumfish.twitter.model.TweetCreated;
import com.traanite.plumfish.twitter.model.TwitterEvents;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.traanite.plumfish.twitter.infrastructure.TwitterEventConfig.TWITTER_EXCHANGE_1;

@RequiredArgsConstructor
public class TwitterEventsAdapter implements TwitterEvents {
    private final RabbitTemplate rabbitTemplate;

    public void publish(@NonNull TweetCreated tweetCreated) {
        rabbitTemplate.convertAndSend(TWITTER_EXCHANGE_1, "", tweetCreated);
    }

}
