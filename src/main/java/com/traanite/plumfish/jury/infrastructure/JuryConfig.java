package com.traanite.plumfish.jury.infrastructure;

import com.traanite.plumfish.commons.events.DomainEvents;
import com.traanite.plumfish.jury.application.RaffleEventsHandler;
import com.traanite.plumfish.jury.application.TwitterEventsHandler;
import com.traanite.plumfish.jury.model.JuryEvents;
import com.traanite.plumfish.jury.model.JuryVerdictResolver;
import com.traanite.plumfish.jury.model.PackageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JuryConfig {

    @Bean
    public PackageRepository packageRepository() {
        return new PackageRepositoryAdapter();
    }

    @Bean
    public RaffleEventsHandler raffleEventsHandler(PackageRepository packageRepository) {
        return new RaffleEventsHandler(packageRepository);
    }

    @Bean
    public JuryEvents juryEvents(DomainEvents domainEvents) {
        return new JuryEventsAdapter(domainEvents);
    }

    @Bean
    public TwitterEventsHandler twitterEventsHandler(PackageRepository packageRepository, JuryEvents juryEvents) {
        return new TwitterEventsHandler(packageRepository, new JuryVerdictResolver(), juryEvents);
    }
}
