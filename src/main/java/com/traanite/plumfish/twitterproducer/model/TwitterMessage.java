package com.traanite.plumfish.twitterproducer.model;

import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class TwitterMessage implements Serializable {
    @NonNull
    String text;
    @NonNull
    TwitterUser twitterUser;
}
