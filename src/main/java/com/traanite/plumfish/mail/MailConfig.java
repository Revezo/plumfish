package com.traanite.plumfish.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

@Configuration
public class MailConfig {

    @Value("${modules.mail.admins}")
    List<String> adminEmails;

    @Bean
    public JuryEventsHandler juryEventsHandler(JavaMailSender javaMailSender) {
        return new JuryEventsHandler(new MailSender(javaMailSender, new MailRecipients(adminEmails)));
    }
}
