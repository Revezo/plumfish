package com.traanite.plumfish.mail

import com.traanite.plumfish.jury.model.MainPrizeWon
import com.traanite.plumfish.jury.model.SecondaryPrizeWon
import com.traanite.plumfish.twitter.model.TwitterUser
import spock.lang.Specification

class JuryEventsHandlerTest extends Specification {
    MailSender mailSender = Mock()
    JuryEventsHandler juryEventsHandler = new JuryEventsHandler(mailSender)

    def "should forward main prize won event to mailSender"() {
        given:
        def mainPrizeWon = new MainPrizeWon(new TwitterUser(2, "user1"))
        when:
        juryEventsHandler.receiveMessage(mainPrizeWon)
        then:
        1 * mailSender.sendMail(mainPrizeWon)
    }

    def "should forward secondary prize won event to mailSender"() {
        given:
        def secondaryPrizeWon = new SecondaryPrizeWon(new TwitterUser(3, "user2"))
        when:
        juryEventsHandler.receiveMessage(secondaryPrizeWon)
        then:
        1 * mailSender.sendMail(secondaryPrizeWon)
    }
}
