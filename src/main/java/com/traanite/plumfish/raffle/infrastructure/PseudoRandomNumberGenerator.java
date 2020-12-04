package com.traanite.plumfish.raffle.infrastructure;

import com.traanite.plumfish.raffle.model.RandomNumberGenerator;
import com.traanite.plumfish.raffle.model.Range;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class PseudoRandomNumberGenerator implements RandomNumberGenerator {

    @Override
    public Optional<Integer> randomInt(Range range) {
        return Optional.of(ThreadLocalRandom.current().nextInt(range.getMin(), range.getMax()));
    }
}
