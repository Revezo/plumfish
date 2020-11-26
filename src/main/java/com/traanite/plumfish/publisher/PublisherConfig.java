package com.traanite.plumfish.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class PublisherConfig {

    private final PublisherProperties publisherProperties;

    @Bean
    public Twitter twitter() {
        String consumerKey = publisherProperties.getTwitterAuthentication().getConsumerKey();
        String consumerSecret = publisherProperties.getTwitterAuthentication().getConsumerSecret();
        String token = publisherProperties.getTwitterAuthentication().getToken();
        String tokenSecret = publisherProperties.getTwitterAuthentication().getTokenSecret();

        TwitterTemplate twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, token, tokenSecret);
        twitterTemplate.streamingOperations().filter("aaa", Collections.singletonList(new PullService()));
        boolean authorized = twitterTemplate.isAuthorized();
        log.info("isAuthorized: " + authorized);
        return twitterTemplate;
    }

}
