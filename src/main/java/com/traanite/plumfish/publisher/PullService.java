package com.traanite.plumfish.publisher;

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
        log.info("onTweet");
    }

    @Override
    public void onDelete(StreamDeleteEvent deleteEvent) {
        log.info("onDelete");
    }

    @Override
    public void onLimit(int numberOfLimitedTweets) {
        log.info("onLimit");
    }

    @Override
    public void onWarning(StreamWarningEvent warningEvent) {
        log.info("onWarning");
    }
}
