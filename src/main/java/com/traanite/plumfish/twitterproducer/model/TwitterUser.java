package com.traanite.plumfish.twitterproducer.model;

import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class TwitterUser implements Serializable {
    @NonNull
    String userId;
    @NonNull
    String userName;

}
