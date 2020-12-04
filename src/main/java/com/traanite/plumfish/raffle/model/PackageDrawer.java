package com.traanite.plumfish.raffle.model;

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
        final Optional<Integer> drawnNumber = randomNumberGenerator.randomInt(new Range(1, 10000));
        final Optional<Integer> drawnStarNumber = randomNumberGenerator.randomInt(new Range(1, stars.getData().size()));
        final Optional<Integer> drawnThingNumber = randomNumberGenerator.randomInt(new Range(1, things.getNames().size()));

        if (!(drawnNumber.isPresent() && drawnStarNumber.isPresent() && drawnThingNumber.isPresent())) {
            return Optional.empty();
        }

        Star drawnStar = starWithHrEqualNumberOrAny(drawnStarNumber.get());
        Thing thing = new Thing(things.getNames().get(drawnThingNumber.get()));

        return Optional.of(new RafflePackage(drawnNumber.get(), drawnStar, thing));
    }

    private Star starWithHrEqualNumberOrAny(int drawnStarNumber) {
        return stars.getData().stream().filter(star -> star.getHr() == drawnStarNumber).findFirst()
                .orElse(stars.getData().stream().findAny().orElseThrow());
    }
}
