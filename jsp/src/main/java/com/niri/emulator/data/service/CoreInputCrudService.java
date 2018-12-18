package com.niri.emulator.data.service;

import com.niri.emulator.data.dto.CoreInputDTO;
import com.niri.emulator.data.entity.CoreInput;
import com.niri.emulator.data.repository.CoreInputRepository;
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
public class CoreInputCrudService implements CrudService<CoreInputDTO> {

    @Autowired
    private ModelMapper modelMapper;

    private CoreInputRepository repository;

    private CoreInputDTO convertToDto(CoreInput coreInput) {
        CoreInputDTO coreInputDto = modelMapper.map(coreInput, CoreInputDTO.class);

        return coreInputDto;
    }

    private CoreInput convertToEntity(CoreInputDTO coreInputDTO) throws ParseException {
        CoreInput coreInput = modelMapper.map(coreInputDTO, CoreInput.class);

        if (coreInputDTO.getId() != null) {
            //CoreInput oldCoreInput = repository.findById(CoreInput.getId()).get();
            /*
             *  Maybe do something if exists.
             *
             */
        }
        return  coreInput;
    }

    private Optional<CoreInputDTO> convertToOptionalDto(Optional<CoreInput> coreInput) {
        Optional<CoreInputDTO> coreDto = Optional.empty();
        if (coreInput.isPresent()) {
            coreDto = Optional.of(modelMapper.map(coreInput.get(), CoreInputDTO.class));
        }
        return coreDto;
    }

    @Autowired
    CoreInputCrudService(CoreInputRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public CoreInputDTO create(CoreInputDTO newEntry) {
        CoreInput created = null;
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
    public Optional<CoreInputDTO> delete(Long id) {
        Optional<CoreInput> deleted = repository.findById(id);

        if (deleted.isPresent()) {
            repository.delete(deleted.get());
        }
        return convertToOptionalDto(deleted);

    }

    @Transactional(readOnly = true)
    @Override
    public List<CoreInputDTO> findAll() {
        List<CoreInput> CoreInputEntries = new ArrayList<>();
        repository.findAll().forEach(CoreInputEntries::add);

        return CoreInputEntries.stream()
                .map(CoreInput -> convertToDto(CoreInput))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CoreInputDTO> findById(Long id) {
        Optional<CoreInput> CoreInput = repository.findById(id);

        return convertToOptionalDto(CoreInput);
    }

    @Transactional
    @Override
    public Optional<CoreInputDTO> update(CoreInputDTO updatedEntry) {
        Optional<CoreInput> coreInput = repository.findById(updatedEntry.getId());

        if (coreInput.isPresent()) {
            coreInput.get().setActionName(updatedEntry.getActionName());
        }
        return convertToOptionalDto(coreInput);
    }
}
