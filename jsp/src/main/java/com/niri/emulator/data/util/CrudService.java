package com.niri.emulator.data.util;

import java.util.List;

public interface CrudService<T> {

    T create(T newEntry);

    T delete(Long id);

    List<T> findAll();

    T findById(Long id);

    T update(T updatedEntry);
}
