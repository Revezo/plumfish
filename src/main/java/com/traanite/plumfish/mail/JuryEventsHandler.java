package com.traanite.plumfish.mail;

import com.traanite.plumfish.jury.model.MainPrizeWon;
import com.traanite.plumfish.jury.model.SecondaryPrizeWon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import static com.traanite.plumfish.jury.infrastructure.JuryEventsConfig.JURY_MAIN_PRIZE_QUEUE;
import static com.traanite.plumfish.jury.infrastructure.JuryEventsConfig.JURY_SECONDARY_PRIZE_QUEUE;

@Slf4j
@RequiredArgsConstructor
public class JuryEventsHandler {

    private final MailSender mailSender;

    @RabbitListener(queues = JURY_MAIN_PRIZE_QUEUE)
    public void receiveMessage(final MainPrizeWon mainPrizeWon) {
        mailSender.sendMail(mainPrizeWon);
        log.info("RECEIVED: {}", mainPrizeWon);
    }

    @RabbitListener(queues = JURY_SECONDARY_PRIZE_QUEUE)
    public void receiveMessage(final SecondaryPrizeWon secondaryPrizeWon) {
        mailSender.sendMail(secondaryPrizeWon);
        log.info("RECEIVED: {}", secondaryPrizeWon);
    }

}
