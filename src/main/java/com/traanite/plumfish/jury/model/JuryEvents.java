package com.traanite.plumfish.jury.model;

public interface JuryEvents {

    void publish(MainPrizeWon mainPrizeWon);

    void publish(SecondaryPrizeWon secondaryPrizeWon);
}
