package com.misterproduct.repository;

import java.util.Optional;

public interface BaseRepository<E> {

    /**
     * Get model by name
     *
     * @param name of product
     * @return product or if not exist empty
     */
    Optional<E> getByName(String name);
}
