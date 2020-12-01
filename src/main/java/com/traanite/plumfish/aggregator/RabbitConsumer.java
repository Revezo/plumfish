package com.traanite.plumfish.aggregator;


import com.traanite.plumfish.raffle.model.RafflePackageDrawn;
import com.traanite.plumfish.twitter.model.TweetCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.traanite.plumfish.raffle.infrastructure.RaffleEventConfig.RAFFLE_QUEUE_1;
import static com.traanite.plumfish.twitter.infrastructure.TwitterEventConfig.TWITTER_QUEUE_1;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitConsumer {

    @RabbitListener(queues = TWITTER_QUEUE_1, concurrency = "3")
    public void receiveMessage1(final TweetCreated tweetCreated) {
        log.trace("RECEIVED: {}", tweetCreated);
    }

    @RabbitListener(queues = RAFFLE_QUEUE_1)
    public void receiveMessage1(final RafflePackageDrawn rafflePackageDrawn) {
        log.info("RECEIVED: {}", rafflePackageDrawn);
    }

}