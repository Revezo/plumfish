package com.traanite.plumfish.raffle.model;

import lombok.Getter;

import java.util.List;

@Getter
public class RandomNumberGeneratorError extends Exception {

    private int code;
    private List<Integer> data;

    public RandomNumberGeneratorError(int code, String message, List<Integer> data) {
        super(message);
        this.code = code;
        this.data = data;
    }
}
