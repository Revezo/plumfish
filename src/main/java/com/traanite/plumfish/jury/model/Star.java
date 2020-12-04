package com.traanite.plumfish.jury.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class Star {
    @JsonProperty("HR")
    int hr;
    @JsonProperty("K")
    int k;
    @JsonProperty("V")
    double v;
}
