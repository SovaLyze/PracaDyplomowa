package com.company.rdzen;

import java.util.Objects;

public abstract class AbstractSortAlgorithm implements SortAlgorithm {

    private final String name;

    protected AbstractSortAlgorithm(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public final String getName() {
        return name;
    }
}
