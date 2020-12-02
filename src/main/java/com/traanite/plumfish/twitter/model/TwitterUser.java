package com.traanite.plumfish.twitter.model;

import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class TwitterUser implements Serializable {
    long userId;
    @NonNull
    String userName;

}
