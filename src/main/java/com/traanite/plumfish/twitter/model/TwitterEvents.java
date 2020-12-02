package com.traanite.plumfish.twitter.model;

public interface TwitterEvents {

    void publish(TweetCreated tweetCreated);
}
