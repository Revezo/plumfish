package com.traanite.plumfish.raffle.model;

import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class Things {
    @NonNull
    List<String> names;
}
