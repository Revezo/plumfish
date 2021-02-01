package com.traanite.plumfish.jury.application

import com.traanite.plumfish.jury.model.*
import com.traanite.plumfish.raffle.model.Star
import com.traanite.plumfish.raffle.model.Thing
import com.traanite.plumfish.twitter.model.TweetCreated
import com.traanite.plumfish.twitter.model.TwitterMessage
import com.traanite.plumfish.twitter.model.TwitterUser
import spock.lang.Specification

class TwitterEventsHandlerTest extends Specification {

    PackageRepository packageRepository = Stub()
    JuryVerdictResolver juryVerdictResolver = Stub()
    JuryEvents juryEvents = Mock()
    TwitterEventsHandler twitterEventsHandler = new TwitterEventsHandler(packageRepository, juryVerdictResolver, juryEvents)

    def "should publish main prize won event"() {
        given:
        def twitterUser = new TwitterUser(2, "userName")
        def twitterMessage = new TwitterMessage("text", twitterUser)
        def tweetCreated = new TweetCreated(twitterMessage)

        def randomPackage = new RandomPackage(33, new Star(2, 3, 4), new Thing("thing1"))
        packageRepository.lastRandomPackage() >> Optional.of(randomPackage)

        def mainPrizeWon = new MainPrizeWon(twitterUser)
        juryVerdictResolver.giveVerdict(twitterMessage, randomPackage) >> Optional.of(mainPrizeWon)
        when:
        twitterEventsHandler.receiveMessage(tweetCreated)
        then:
        1 * juryEvents.publish(mainPrizeWon)
    }

    def "should publish secondary prize won event"() {
        given:
        def twitterUser = new TwitterUser(2, "userName")
        def twitterMessage = new TwitterMessage("text", twitterUser)
        def tweetCreated = new TweetCreated(twitterMessage)

        def randomPackage = new RandomPackage(33, new Star(2, 3, 4), new Thing("thing1"))
        packageRepository.lastRandomPackage() >> Optional.of(randomPackage)

        def secondaryPrizeWon = new SecondaryPrizeWon(twitterUser)
        juryVerdictResolver.giveVerdict(twitterMessage, randomPackage) >> Optional.of(secondaryPrizeWon)
        when:
        twitterEventsHandler.receiveMessage(tweetCreated)
        then:
        1 * juryEvents.publish(secondaryPrizeWon)
    }

    def "should publish nothing"() {
        given:
        def twitterUser = new TwitterUser(2, "userName")
        def twitterMessage = new TwitterMessage("text", twitterUser)
        def tweetCreated = new TweetCreated(twitterMessage)

        def randomPackage = new RandomPackage(33, new Star(2, 3, 4), new Thing("thing1"))
        packageRepository.lastRandomPackage() >> Optional.of(randomPackage)

        juryVerdictResolver.giveVerdict(twitterMessage, randomPackage) >> Optional.ofNullable(null)
        when:
        twitterEventsHandler.receiveMessage(tweetCreated)
        then:
        0 * juryEvents.publish(_)
    }
}
