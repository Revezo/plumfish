package com.traanite.plumfish.raffle.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traanite.plumfish.commons.events.DomainEvents;
import com.traanite.plumfish.commons.events.exception.ResourceFileNotFoundException;
import com.traanite.plumfish.raffle.application.DrawPackageScheduler;
import com.traanite.plumfish.raffle.model.PackageDrawer;
import com.traanite.plumfish.raffle.model.RandomNumberGenerator;
import com.traanite.plumfish.raffle.model.Stars;
import com.traanite.plumfish.raffle.model.Things;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStreamReader;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class RaffleConfig {
    private static final String THINGS_FILE = "data/things.json";
    private static final String STARS_FILE = "data/bright-stars-short-v5.json";

    private final RaffleProperties properties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public RandomNumberGenerator randomNumberGenerator() {
//        return new RandomOrgTrueNumberGenerator(new RestTemplate(), properties.getRandomApiKey());
        return new PseudoRandomNumberGenerator();
    }

    @Bean
    public DrawPackageScheduler testScheduler(DomainEvents domainEvents, RandomNumberGenerator randomNumberGenerator) {
        return new DrawPackageScheduler(new PackageDrawer(things(), stars(), randomNumberGenerator), new RaffleEventsAdapter(domainEvents));
    }

    public Things things() {
        Resource resource = new ClassPathResource(THINGS_FILE);
        try (InputStreamReader in = new InputStreamReader(resource.getInputStream())) {
            Things things = objectMapper.readValue(in, Things.class);
            return new Things(Collections.unmodifiableList(things.getNames()));
        } catch (Exception e) {
            throw new ResourceFileNotFoundException(e, THINGS_FILE);
        }
    }

    public Stars stars() {
        Resource resource = new ClassPathResource(STARS_FILE);
        try (InputStreamReader in = new InputStreamReader(resource.getInputStream())) {
            Stars stars = objectMapper.readValue(in, Stars.class);
            return new Stars(Collections.unmodifiableList(stars.getData()));
        } catch (Exception e) {
            throw new ResourceFileNotFoundException(e, STARS_FILE);
        }
    }
}
