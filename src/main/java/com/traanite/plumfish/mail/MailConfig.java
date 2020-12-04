package com.traanite.plumfish.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    @Bean
    public JuryEventsHandler juryEventsHandler() {
        return new JuryEventsHandler();
    }
}
