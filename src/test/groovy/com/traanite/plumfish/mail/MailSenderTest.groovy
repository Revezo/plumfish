package com.traanite.plumfish.mail

import org.springframework.mail.javamail.JavaMailSender
import spock.lang.Specification

class MailSenderTest extends Specification {
    JavaMailSender javaMailSender = Mock()
    MailRecipients mailRecipients = new MailRecipients(Arrays.asList("example1@mail.com", "example2@mail.com"))
    MailSender mailSender = new MailSender(javaMailSender, mailRecipients)

    // TODO
}
