package com.traanite.plumfish.twitter.infrastructure;

import com.traanite.plumfish.commons.events.DomainEvents;
import com.traanite.plumfish.twitter.application.TwitterStreamListener;
import com.traanite.plumfish.twitter.model.TwitterEvents;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TwitterConfig {

    private final TwitterProperties twitterProperties;

    @Bean
    public Twitter twitter() {
        String consumerKey = twitterProperties.getTwitterAuthentication().getConsumerKey();
        String consumerSecret = twitterProperties.getTwitterAuthentication().getConsumerSecret();
        String token = twitterProperties.getTwitterAuthentication().getToken();
        String tokenSecret = twitterProperties.getTwitterAuthentication().getTokenSecret();

        TwitterTemplate twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, token, tokenSecret);
        validateTwitterAuthorization(twitterTemplate);
        return twitterTemplate;
    }

    @Bean
    public Stream twitsPullStream(Twitter twitter, TwitterEvents twitterEvents) {
        return twitter.streamingOperations().sample(Collections.singletonList(new TwitterStreamListener(twitterEvents)));
    }

    @Bean
    public TwitterEvents twitterEvents(DomainEvents domainEvents) {
        return new TwitterEventsAdapter(domainEvents);
    }

    private void validateTwitterAuthorization(Twitter twitter) {
        boolean isTwitterAuthorized = twitter.isAuthorized();
        if (!isTwitterAuthorized) {
            throw new InvalidAuthorizationException("twitter", "Couldn't authorize twitter connection");
        }
    }
}
