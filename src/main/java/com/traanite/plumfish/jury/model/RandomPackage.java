package com.traanite.plumfish.jury.model;

import com.traanite.plumfish.raffle.model.Star;
import com.traanite.plumfish.raffle.model.Thing;
import lombok.NonNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class RandomPackage implements Serializable {
    int number;
    @NonNull
    Star star;
    @NonNull
    Thing thing;
}
