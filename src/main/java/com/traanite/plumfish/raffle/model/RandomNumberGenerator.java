package com.traanite.plumfish.raffle.model;

import com.traanite.plumfish.commons.events.exception.NullResponseException;

public interface RandomNumberGenerator {

    int randomInt(Range range) throws NullResponseException, RandomNumberGeneratorError;
}
