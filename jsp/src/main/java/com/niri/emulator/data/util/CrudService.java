package com.niri.emulator.data.util;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    T create(T newEntry);

    Optional<T> delete(Long id);

    List<T> findAll();

    Optional<T> findById(Long id);

    Optional<T> update(T updatedEntry);
}
