package com.traanite.plumfish.raffle.model;

import java.util.Optional;

public interface RandomNumberGenerator {

    Optional<Integer> randomInt(Range range);
}
