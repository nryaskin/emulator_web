package com.niri.emulator.data.repository;

import com.niri.emulator.data.entity.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {
}
