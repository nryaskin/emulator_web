package com.niri.emulator.data.util;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    T create(T newEntry);

    Optional<T> delete(Long id);

    List<T> findAll();

    Optional<T> findById(Long id);

    Page<T> findPaginated(int page, int size);

    Optional<T> update(T updatedEntry);
}
