package com.traanite.plumfish.raffle.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "modules.raffle")
@Getter
@Setter
@ToString
public class StellarProducerProperties {

    private String randomApiKey;

}
