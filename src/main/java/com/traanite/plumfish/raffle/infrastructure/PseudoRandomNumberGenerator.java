package com.traanite.plumfish.raffle.infrastructure;

import com.traanite.plumfish.commons.events.exception.NullResponseException;
import com.traanite.plumfish.raffle.model.RandomNumberGenerator;
import com.traanite.plumfish.raffle.model.RandomNumberGeneratorError;
import com.traanite.plumfish.raffle.model.Range;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class PseudoRandomNumberGenerator implements RandomNumberGenerator {

    @Override
    public int randomInt(Range range) throws NullResponseException, RandomNumberGeneratorError {
        return ThreadLocalRandom.current().nextInt(range.getMin(), range.getMax() + 1);
    }
}
