package com.traanite.plumfish.raffle.service;

import com.traanite.plumfish.raffle.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class PackageDrawer {

    private final Things things;
    private final Stars stars;
    private final RandomNumberGenerator randomNumberGenerator;

    public Optional<DrawPackage> drawPackage() {
        final int drawnNumber = randomIntOrLogError(new Range(1, 10000));
        final int drawnStarNumber = randomIntOrLogError(new Range(1, stars.getData().size()));
        final int drawnThingNumber = randomIntOrLogError(new Range(1, things.getNames().size()));

        if (!allNumbersValid(drawnNumber, drawnStarNumber, drawnThingNumber)) {
            return Optional.empty();
        }

        Star drawnStar = stars.getData().stream().filter(star -> star.getHr() == drawnStarNumber).findFirst()
                .orElse(stars.getData().stream().findAny().orElseThrow());
        Thing thing = new Thing(things.getNames().get(drawnThingNumber));

        return Optional.of(new DrawPackage(drawnNumber, drawnStar, thing));
    }

    private boolean allNumbersValid(int drawnNumber, int drawnStarNumber, int drawnThingNumber) {
        return drawnNumber != 0 && drawnStarNumber != 0 && drawnThingNumber != 0;
    }

    private int randomIntOrLogError(Range range) {
        try {
            return randomNumberGenerator.randomInt(range);
        } catch (RandomNumberGeneratorException e) {
            log.error("Couldn't get random number", e);
            return 0;
        }
    }

}
