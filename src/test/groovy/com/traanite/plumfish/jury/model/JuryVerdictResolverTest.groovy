package com.traanite.plumfish.jury.model


import com.traanite.plumfish.raffle.model.Star
import com.traanite.plumfish.raffle.model.Thing
import com.traanite.plumfish.twitter.model.TwitterMessage
import com.traanite.plumfish.twitter.model.TwitterUser
import spock.lang.Specification

class JuryVerdictResolverTest extends Specification {
    final static String textLength100 = "text with length 100text with length 100text with length 100text with length 100text with length 100"
    JuryVerdictResolver juryVerdictResolver = new JuryVerdictResolver()

    def "should return main prize won event for message text length plus star hr equal 6"() {
        given:
        def twitterUser = new TwitterUser(2, "username")
        def twitterMessage = new TwitterMessage("text", twitterUser)
        def randomPackage = new RandomPackage(2, new Star(2, 1, 1.2), new Thing("thing1"))
        when:
        def verdict = juryVerdictResolver.giveVerdict(twitterMessage, randomPackage)
        then:
        verdict.get() instanceof MainPrizeWon
        def mainPrizeWon = verdict.get() as MainPrizeWon
        mainPrizeWon.twitterUser == twitterUser
    }

    def "should return secondary prize won event for twitter message text with length 100"() {
        given:
        def twitterUser = new TwitterUser(2, "username")
        def twitterMessage = new TwitterMessage(textLength100, twitterUser)
        def randomPackage = new RandomPackage(2, new Star(2, 1, 1.2), new Thing("thing1"))
        when:
        def verdict = juryVerdictResolver.giveVerdict(twitterMessage, randomPackage)
        then:
        verdict.get() instanceof SecondaryPrizeWon
        def secondaryPrizeWon = verdict.get() as SecondaryPrizeWon
        secondaryPrizeWon.twitterUser == twitterUser
    }

    def "should return empty verdict for other scenario"() {
        given:
        def twitterUser = new TwitterUser(2, "username")
        def twitterMessage = new TwitterMessage("some text", twitterUser)
        def randomPackage = new RandomPackage(2, new Star(2, 1, 1.2), new Thing("thing1"))
        when:
        def verdict = juryVerdictResolver.giveVerdict(twitterMessage, randomPackage)
        then:
        verdict.isEmpty()
    }
}
