package com.traanite.plumfish.jury.application;


import com.traanite.plumfish.jury.model.*;
import com.traanite.plumfish.twitter.model.TweetCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import static com.traanite.plumfish.twitter.infrastructure.TwitterEventsConfig.TWITTER_QUEUE_1;

@Slf4j
@RequiredArgsConstructor
public class TwitterEventsHandler {

    private final PackageRepository packageRepository;
    private final JuryVerdictResolver juryVerdictResolver;
    private final JuryEvents juryEvents;

    @RabbitListener(queues = TWITTER_QUEUE_1, concurrency = "3")
    public void receiveMessage(final TweetCreated tweetCreated) {
        log.trace("RECEIVED: {}", tweetCreated);
        packageRepository.lastRandomPackage().flatMap(randomPackage -> juryVerdictResolver.giveVerdict(tweetCreated.getTwitterMessage(), randomPackage))
                .ifPresent(domainEvent -> {
                    // TODO such instance checks can be quite nicely replaced with vavr.io
                    if (domainEvent instanceof MainPrizeWon) {
                        juryEvents.publish((MainPrizeWon) domainEvent);
                    } else if (domainEvent instanceof SecondaryPrizeWon) {
                        juryEvents.publish((SecondaryPrizeWon) domainEvent);
                    }
                });
    }
}