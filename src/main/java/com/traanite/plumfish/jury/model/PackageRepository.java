package com.traanite.plumfish.jury.model;

import java.util.Optional;

public interface PackageRepository {

    Optional<RandomPackage> lastRandomPackage();

    void updateLastPackage(RandomPackage randomPackage);
}
