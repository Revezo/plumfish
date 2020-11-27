package com.traanite.plumfish.twitterproducer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;

@Slf4j
@RequiredArgsConstructor
public class PullService implements StreamListener {

    @Override
    public void onTweet(Tweet tweet) {
        if(tweet.getLanguageCode().equals("en")) {
            log.info(tweet.getText());
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

