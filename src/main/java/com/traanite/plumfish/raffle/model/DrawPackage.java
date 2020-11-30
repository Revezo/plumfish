package com.traanite.plumfish.raffle.model;

import lombok.Value;

import java.io.Serializable;

@Value
public class DrawPackage implements Serializable {
    int number;
    Star star;
    Thing thing;
}
