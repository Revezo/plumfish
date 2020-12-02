package com.traanite.plumfish.raffle.model;

import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class RafflePackage implements Serializable {
    int number;
    @NonNull
    Star star;
    @NonNull
    Thing thing;
}
