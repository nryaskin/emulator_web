package com.niri.emulator.data.coreinput;

import com.niri.emulator.data.coreinput.CoreInput;
import com.niri.emulator.data.coreinput.CoreInputDTO;
import com.niri.emulator.data.coreinput.CoreInputRepository;
import com.niri.emulator.data.util.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoreInputCrudService implements CrudService<CoreInputDTO> {

    @Autowired
    private ModelMapper modelMapper;

    private CoreInputRepository repository;

    private CoreInputDTO convertToDto(CoreInput CoreInput) {
        CoreInputDTO CoreInputDto = modelMapper.map(CoreInput, CoreInputDTO.class);

        return CoreInputDto;
    }

    private CoreInput convertToEntity(CoreInputDTO CoreInputDTO) throws ParseException {
        CoreInput CoreInput = modelMapper.map(CoreInputDTO, CoreInput.class);

        if (CoreInputDTO.getId() != null) {
            //CoreInput oldCoreInput = repository.findById(CoreInput.getId()).get();
            /*
             *  Maybe do something if exists.
             *
             */
        }
        return  CoreInput;
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
    public CoreInputDTO delete(Long id) {
        CoreInput deleted = repository.findById(id).get();

        repository.delete(deleted);

        return convertToDto(deleted);

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
    public CoreInputDTO findById(Long id) {
        CoreInput CoreInput = repository.findById(id).get();

        return convertToDto(CoreInput);
    }

    @Transactional
    @Override
    public CoreInputDTO update(CoreInputDTO updatedEntry) {
        CoreInput coreInput = repository.findById(updatedEntry.getId()).get();

        coreInput.setActionName(updatedEntry.getActionName());

        return convertToDto(coreInput);
    }
}
