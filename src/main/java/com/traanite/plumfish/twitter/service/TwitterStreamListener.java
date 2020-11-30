package com.traanite.plumfish.twitter.service;

import com.traanite.plumfish.twitter.model.TweetCreatedEvent;
import com.traanite.plumfish.twitter.model.TwitterMessage;
import com.traanite.plumfish.twitter.model.TwitterUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;


@Slf4j
@RequiredArgsConstructor
public class TwitterStreamListener implements StreamListener {

    private final TwitterEventPublisher twitterEventPublisher;

    @Override
    public void onTweet(Tweet tweet) {
        if (tweet.getLanguageCode().equals("en")) {
            twitterEventPublisher.publish(new TweetCreatedEvent(
                    new TwitterMessage(tweet.getText(), new TwitterUser(tweet.getUser().getId(), tweet.getUser().getName()))));
        }
    }

    @Override
    public void onDelete(StreamDeleteEvent deleteEvent) {
        // not needed
    }

    @Override
    public void onLimit(int numberOfLimitedTweets) {
        // not needed
    }

    @Override
    public void onWarning(StreamWarningEvent warningEvent) {
        // not needed
    }
}

