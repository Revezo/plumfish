package com.traanite.plumfish.aggregator;


import com.traanite.plumfish.raffle.model.DrawPackageCreatedEvent;
import com.traanite.plumfish.twitter.model.TweetCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.traanite.plumfish.raffle.config.RaffleEventConfig.RAFFLE_QUEUE_1;
import static com.traanite.plumfish.twitter.config.TwitterEventConfig.TWITTER_QUEUE_1;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitConsumer {

    @RabbitListener(queues = TWITTER_QUEUE_1, concurrency = "3")
    public void receiveMessage1(final TweetCreatedEvent tweetCreatedEvent) {
        log.trace("RECEIVED: {}", tweetCreatedEvent);
    }

    @RabbitListener(queues = RAFFLE_QUEUE_1)
    public void receiveMessage1(final DrawPackageCreatedEvent drawPackageCreatedEvent) {
        log.info("RECEIVED: {}", drawPackageCreatedEvent);
    }

}