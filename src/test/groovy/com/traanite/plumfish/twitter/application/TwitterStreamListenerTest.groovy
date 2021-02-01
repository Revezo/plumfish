package com.traanite.plumfish.twitter.application


import com.traanite.plumfish.twitter.model.TweetCreated
import com.traanite.plumfish.twitter.model.TwitterEvents
import org.springframework.social.twitter.api.StreamDeleteEvent
import org.springframework.social.twitter.api.StreamWarningEvent
import org.springframework.social.twitter.api.Tweet
import org.springframework.social.twitter.api.TwitterProfile
import spock.lang.Specification

class TwitterStreamListenerTest extends Specification {

    TwitterEvents twitterEvents = Mock()
    TwitterStreamListener twitterStreamListener = new TwitterStreamListener(twitterEvents)

    def "should publish tweet created when onTweet language is en"() {
        given:
        def tweet = tweetWithLanguage "en"

        when:
        twitterStreamListener.onTweet(tweet)

        then:
        1 * twitterEvents.publish({ TweetCreated it ->
            it.twitterMessage.text == tweet.getText() &&
                    it.twitterMessage.creator.userId == tweet.user.id &&
                    it.twitterMessage.creator.userName == tweet.user.name
        } as TweetCreated)
    }

    def "should not publish tweet created when onTweet language is not en"() {
        given:
        def tweet = tweetWithLanguage "de"

        when:
        twitterStreamListener.onTweet(tweet)

        then:
        0 * twitterEvents.publish(_ as TweetCreated)
    }

    def "should not publish anything when onDelete"() {
        given:
        def deleteEvent = new StreamDeleteEvent(1L, 2L)
        when:
        twitterStreamListener.onDelete(deleteEvent)
        then:
        0 * twitterEvents.publish(_ as Object)
    }

    def "should not publish anything when onLimit"() {
        given:
        int numberOfLimitedTweets = 0
        when:
        twitterStreamListener.onLimit(numberOfLimitedTweets)
        then:
        0 * twitterEvents.publish(_ as Object)
    }

    def "should not publish anything when onWarning"() {
        given:
        def streamWarningEvent = new StreamWarningEvent("code", "message", 2.0)
        when:
        twitterStreamListener.onWarning(streamWarningEvent)
        then:
        0 * twitterEvents.publish(_ as Object)
    }

    static Tweet tweetWithLanguage(String language) {
        Tweet tweet = new Tweet(1L, "idStr", "tweetText", new Date(), "user", "", 2L, 3L,
                language, "source")
        TwitterProfile twitterProfile = new TwitterProfile(2L, "screenName", "name", "url", "profileImageUrl",
                "description", "location", new Date(2010, 3, 1))
        tweet.setUser(twitterProfile)
        return tweet
    }
}
