package com.niri.emulator.data.repository;

import com.niri.emulator.data.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
