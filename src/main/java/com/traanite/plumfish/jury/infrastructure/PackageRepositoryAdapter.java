package com.traanite.plumfish.jury.infrastructure;

import com.traanite.plumfish.jury.model.PackageRepository;
import com.traanite.plumfish.jury.model.RandomPackage;

import java.util.Optional;

public class PackageRepositoryAdapter implements PackageRepository {

    private RandomPackage lastRandomPackage;

    @Override
    public Optional<RandomPackage> lastRandomPackage() {
        return Optional.ofNullable(lastRandomPackage);
    }

    @Override
    public void updateLastPackage(RandomPackage randomPackage) {
        lastRandomPackage = randomPackage;
    }
}
