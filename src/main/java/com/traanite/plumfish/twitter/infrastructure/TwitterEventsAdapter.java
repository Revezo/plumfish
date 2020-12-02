package com.traanite.plumfish.twitter.infrastructure;

import com.traanite.plumfish.commons.events.DomainEvents;
import com.traanite.plumfish.twitter.model.TweetCreated;
import com.traanite.plumfish.twitter.model.TwitterEvents;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.traanite.plumfish.twitter.infrastructure.TwitterEventsConfig.TWITTER_EXCHANGE_1;

@RequiredArgsConstructor
public class TwitterEventsAdapter implements TwitterEvents {
    private final DomainEvents domainEvents;

    public void publish(@NonNull TweetCreated tweetCreated) {
        domainEvents.publish(tweetCreated, TWITTER_EXCHANGE_1, "");
    }

}
