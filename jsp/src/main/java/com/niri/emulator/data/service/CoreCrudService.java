package com.niri.emulator.data.service;

import com.niri.emulator.data.dto.CoreDTO;
import com.niri.emulator.data.entity.Core;
import com.niri.emulator.data.repository.CoreRepository;
import com.niri.emulator.data.util.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoreCrudService implements CrudService<CoreDTO> {

    private final CoreRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private CoreDTO convertToDto(Core core) {
        CoreDTO coreDto = modelMapper.map(core, CoreDTO.class);
        return coreDto;
    }

    private Optional<CoreDTO> convertToOptionalDto(Optional<Core> core) {
        Optional<CoreDTO> coreDto = Optional.empty();
        if (core.isPresent()) {
            coreDto = Optional.of(modelMapper.map(core.get(), CoreDTO.class));
        }
        return coreDto;
    }

    private Core convertToEntity(CoreDTO coreDTO) throws ParseException {
        Core core = modelMapper.map(coreDTO, Core.class);

        return core;
    }

    @Autowired
    CoreCrudService(CoreRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public CoreDTO create(CoreDTO newEntry) {
        Core created = null;
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
    public Optional<CoreDTO> delete(Long id) {
        Optional<Core> deleted = repository.findById(id);

        if (deleted.isPresent()) {
            repository.delete(deleted.get());
        }

        return convertToOptionalDto(deleted);

    }

    @Transactional(readOnly = true)
    @Override
    public List<CoreDTO> findAll() {
        List<Core> coreEntries = new ArrayList<>();
        repository.findAll().forEach(coreEntries::add);

        return coreEntries.stream()
                .map(core -> convertToDto(core))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CoreDTO> findById(Long id) {
        Optional<Core> core = repository.findById(id);

        return convertToOptionalDto(core);
    }

    @Override
    public Page<CoreDTO> findPaginated(int page, int size) {
        throw new NotImplementedException();
    }

    @Transactional
    @Override
    public Optional<CoreDTO> update(CoreDTO updatedEntry) {
        Optional<Core> core = repository.findById(updatedEntry.getId());

        if (core.isPresent()) {
            core.get().update(updatedEntry.getCoreName());
        }
        return convertToOptionalDto(core);
    }
}
