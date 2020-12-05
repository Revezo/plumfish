package com.traanite.plumfish.mail;

import com.traanite.plumfish.commons.events.DomainEvent;
import com.traanite.plumfish.jury.model.MainPrizeWon;
import com.traanite.plumfish.jury.model.SecondaryPrizeWon;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RequiredArgsConstructor
public class MailSender {

    private final JavaMailSender javaMailSender;
    private final MailRecipients recipients;

    public void sendMail(DomainEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@plumfish.com");
        message.setTo(recipients.getEmails().toArray(String[]::new));
        if (event instanceof MainPrizeWon) {
            message.setSubject("Plumfish message: MainPrizeWon");
            message.setText(toMailText((MainPrizeWon) event));
        } else if (event instanceof SecondaryPrizeWon) {
            message.setSubject("Plumfish message: SecondaryPrizeWon");
            message.setText(toMailText((SecondaryPrizeWon) event));
        }
        javaMailSender.send(message);
    }

    private String toMailText(MainPrizeWon mainPrizeWon) {
        return mainPrizeWon.toString();

    }

    private String toMailText(SecondaryPrizeWon secondaryPrizeWon) {
        return secondaryPrizeWon.toString();
    }
}
