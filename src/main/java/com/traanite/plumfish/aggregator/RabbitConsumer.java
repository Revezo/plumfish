package com.traanite.plumfish.aggregator;


import com.traanite.plumfish.twitterproducer.model.TwitterMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.traanite.plumfish.twitterproducer.config.MessagingConfig.TWITTER_QUEUE_1;

@Service
@Slf4j
public class RabbitConsumer {

    @RabbitListener(queues = TWITTER_QUEUE_1, concurrency = "3")
    public void receiveMessage1(final TwitterMessageEvent twitterMessageEvent) {
        log.info("RECEIVED: {}", twitterMessageEvent);
    }

}