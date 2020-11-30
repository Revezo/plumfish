package com.traanite.plumfish.twitter.config;

import com.traanite.plumfish.twitter.service.TwitterEventPublisher;
import com.traanite.plumfish.twitter.service.TwitterStreamListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class TwitterProducerConfig {

    private final TwitterProducerProperties twitterPublisherProperties;

    @Bean
    public Twitter twitter() {
        String consumerKey = twitterPublisherProperties.getTwitterAuthentication().getConsumerKey();
        String consumerSecret = twitterPublisherProperties.getTwitterAuthentication().getConsumerSecret();
        String token = twitterPublisherProperties.getTwitterAuthentication().getToken();
        String tokenSecret = twitterPublisherProperties.getTwitterAuthentication().getTokenSecret();

        TwitterTemplate twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, token, tokenSecret);
        validateTwitterAuthorization(twitterTemplate);
        return twitterTemplate;
    }

    @Bean
    public Stream twitsPullStream(Twitter twitter, TwitterEventPublisher twitterEventPublisher) {
        return twitter.streamingOperations().sample(Collections.singletonList(new TwitterStreamListener(twitterEventPublisher)));
    }

    @Bean
    public TwitterEventPublisher tweeterEventPublisher(RabbitTemplate rabbitTemplate) {
        return new TwitterEventPublisher(rabbitTemplate);
    }

    private void validateTwitterAuthorization(Twitter twitter) {
        boolean isTwitterAuthorized = twitter.isAuthorized();
        if (!isTwitterAuthorized) {
            throw new InvalidAuthorizationException("twitter", "Couldn't authorize twitter connection");
        }
    }
}
