package com.traanite.plumfish.raffle.model;

import com.traanite.plumfish.commons.events.exception.NullResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class PackageDrawer {

    private final Things things;
    private final Stars stars;
    private final RandomNumberGenerator randomNumberGenerator;

    public Optional<RafflePackage> drawPackage() {
        final Integer drawnNumber = randomIntOrLogError(new Range(1, 10000));
        final Integer drawnStarNumber = randomIntOrLogError(new Range(1, stars.getData().size()));
        final Integer drawnThingNumber = randomIntOrLogError(new Range(1, things.getNames().size()));

        if (!allNumbersValid(drawnNumber, drawnStarNumber, drawnThingNumber)) {
            return Optional.empty();
        }

        Star drawnStar = starWithHrEqualNumberOrAny(drawnStarNumber);
        Thing thing = new Thing(things.getNames().get(drawnThingNumber));

        return Optional.of(new RafflePackage(drawnNumber, drawnStar, thing));
    }

    private Star starWithHrEqualNumberOrAny(int drawnStarNumber) {
        return stars.getData().stream().filter(star -> star.getHr() == drawnStarNumber).findFirst()
                .orElse(stars.getData().stream().findAny().orElseThrow());
    }

    private boolean allNumbersValid(Integer drawnNumber, Integer drawnStarNumber, Integer drawnThingNumber) {
        return drawnNumber != null && drawnStarNumber != null && drawnThingNumber != null;
    }

    private Integer randomIntOrLogError(Range range) {
        try {
            return randomNumberGenerator.randomInt(range);
        } catch (RandomNumberGeneratorError | NullResponseException e) {
            log.error("Couldn't get random number", e);
            return null;
        }
    }

}
