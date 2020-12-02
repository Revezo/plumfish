package com.traanite.plumfish.raffle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.io.Serializable;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class Star implements Serializable {
    @JsonProperty("HR")
    int hr;
    @JsonProperty("K")
    int k;
    @JsonProperty("V")
    double v;
}
