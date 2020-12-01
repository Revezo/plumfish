package com.traanite.plumfish.raffle.model;

import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class Thing implements Serializable {
    @NonNull
    String name;
}
