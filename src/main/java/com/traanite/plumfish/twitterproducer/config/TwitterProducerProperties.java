package com.traanite.plumfish.twitterproducer.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "modules.producer")
@Getter
@Setter
@ToString
class TwitterProducerProperties {

    @NestedConfigurationProperty
    private TwitterAuthentication twitterAuthentication;

}

@Getter
@Setter
@ToString
class TwitterAuthentication {
    private String consumerKey;
    private String consumerSecret;
    private String token;
    private String tokenSecret;

}
