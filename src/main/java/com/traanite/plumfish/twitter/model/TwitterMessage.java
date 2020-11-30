package com.traanite.plumfish.twitter.model;

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
