package com.niri.emulator.data.service;

import com.niri.emulator.data.dto.KeyMapDTO;
import com.niri.emulator.data.entity.KeyMap;
import com.niri.emulator.data.repository.KeyMapRepository;
import com.niri.emulator.data.util.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KeyMapCrudService implements CrudService<KeyMapDTO> {

    @Autowired
    private ModelMapper modelMapper;

    private final KeyMapRepository repository;

    private KeyMapDTO convertToDto(KeyMap keyMap) {
        KeyMapDTO keyMapDto = modelMapper.map(keyMap, KeyMapDTO.class);
        return keyMapDto;
    }

    private Optional<KeyMapDTO> convertToOptionalDto(Optional<KeyMap> keyMap) {
        Optional<KeyMapDTO> keyMapDto = Optional.empty();
        if (keyMap.isPresent()) {
            keyMapDto = Optional.of(modelMapper.map(keyMap, KeyMapDTO.class));
        }
        return keyMapDto;
    }

    private KeyMap convertToEntity(KeyMapDTO keyMapDTO) throws ParseException {
        KeyMap keyMap = modelMapper.map(keyMapDTO, KeyMap.class);

        return keyMap;
    }

    public KeyMapCrudService(KeyMapRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public KeyMapDTO create(KeyMapDTO newEntry) {
        KeyMap created = null;
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
    public Optional<KeyMapDTO> delete(Long id) {
        Optional<KeyMap> deleted = repository.findById(id);

        if (deleted.isPresent()) {
            repository.delete(deleted.get());
        }

        return convertToOptionalDto(deleted);
    }

    @Transactional(readOnly = true)
    @Override
    public List<KeyMapDTO> findAll() {
        List<KeyMap> keyMapEntries = new ArrayList<>();
        repository.findAll().forEach(keyMapEntries::add);

        return keyMapEntries.stream()
                .map(keyMap -> convertToDto(keyMap))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<KeyMapDTO> findById(Long id) {
        Optional<KeyMap> keyMap = repository.findById(id);

        return convertToOptionalDto(keyMap);
    }

    @Override
    public Page<KeyMapDTO> findPaginated(int page, int size, Sort sort) {
        throw new NotImplementedException();
    }

    @Transactional
    @Override
    public Optional<KeyMapDTO> update(KeyMapDTO updatedEntry) {
        Optional<KeyMap> keyMap = repository.findById(updatedEntry.getId());

        if (keyMap.isPresent()) {
            KeyMap entity = keyMap.get();
            updatedEntry.setId(entity.getId());
            keyMap = Optional.of(repository.save(convertToEntity(updatedEntry)));
        }
        return convertToOptionalDto(keyMap);
    }
}
