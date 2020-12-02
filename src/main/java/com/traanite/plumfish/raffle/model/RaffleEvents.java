package com.traanite.plumfish.raffle.model;

import lombok.NonNull;

public interface RaffleEvents {

    void publish(RafflePackageDrawn rafflePackageDrawn);

    void publish(@NonNull RafflePackageDrawFailed rafflePackageDrawFailed);
}
