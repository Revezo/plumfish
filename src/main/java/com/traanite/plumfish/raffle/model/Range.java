package com.traanite.plumfish.raffle.model;

import lombok.Value;

import java.io.Serializable;

@Value
public class Range implements Serializable {
    int min;
    int max;

    public Range(int min, int max) {
        if (min < max) {
            this.min = min;
            this.max = max;
        } else {
            this.min = max;
            this.max = min;
        }
    }
}
