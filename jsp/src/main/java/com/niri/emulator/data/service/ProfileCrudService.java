package com.niri.emulator.data.service;

import com.niri.emulator.data.dto.ProfileDTO;
import com.niri.emulator.data.entity.Profile;
import com.niri.emulator.data.repository.ProfileRepository;
import com.niri.emulator.data.util.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileCrudService implements CrudService<ProfileDTO> {

    @Autowired
    private ModelMapper modelMapper;

    private final ProfileRepository repository;

    private ProfileDTO convertToDto(Profile profile) {
        ProfileDTO profileDto = modelMapper.map(profile, ProfileDTO.class);
        return profileDto;
    }

    private Optional<ProfileDTO> convertToOptionalDto(Optional<Profile> profile) {
        Optional<ProfileDTO> profileDto = Optional.empty();
        if (profile.isPresent()) {
            profileDto = Optional.of(modelMapper.map(profile.get(), ProfileDTO.class));
        }
        return profileDto;
    }

    private Profile convertToEntity(ProfileDTO profileDTO) throws ParseException {
        Profile profile = modelMapper.map(profileDTO, Profile.class);

        return profile;
    }
    
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProfileCrudService(ProfileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public ProfileDTO create(ProfileDTO newEntry) {
        Profile created = null;
        try {
            created = convertToEntity(newEntry);
            created = repository.save(created);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertToDto(created);
    }

    @Transactional
    @Override
    public Optional<ProfileDTO> delete(Long id) {
        Optional<Profile> deleted = repository.findById(id);

        if (deleted.isPresent()) {
            repository.delete(deleted.get());
        }

        return convertToOptionalDto(deleted);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfileDTO> findAll() {
        List<Profile> profileEntries = new ArrayList<>();
        repository.findAll().forEach(profileEntries::add);

        return profileEntries.stream()
                .map(profile -> convertToDto(profile))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ProfileDTO> findById(Long id) {
        Optional<Profile> profile = repository.findById(id);

        return convertToOptionalDto(profile);
    }

    @Transactional
    @Override
    public Optional<ProfileDTO> update(ProfileDTO updatedEntry) {
        Optional<Profile> profile = repository.findById(updatedEntry.getId());

        if (profile.isPresent()) {
            Profile entity = profile.get();
            updatedEntry.setId(entity.getId());
            profile = Optional.of(repository.save(convertToEntity(updatedEntry)));
        }
        return convertToOptionalDto(profile);
    }


}
