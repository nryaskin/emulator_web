package com.niri.emulator.data.core;

import com.niri.emulator.data.coreinput.CoreInputDTO;
import com.niri.emulator.data.util.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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

    private Core convertToEntity(CoreDTO coreDTO) throws ParseException {
        Core core = modelMapper.map(coreDTO, Core.class);

        if (coreDTO.getId() != null) {
            Core oldCore = repository.findOne(core.getId());
            /*
             *  Maybe do something if exists.
             *
             */
        }
        return  core;
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
    public CoreDTO delete(Long id) {
        Core deleted = repository.findOne(id);

        repository.delete(deleted);

        return convertToDto(deleted);

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
    public CoreDTO findById(Long id) {
        Core core = repository.findOne(id);

        return convertToDto(core);
    }

    @Transactional
    @Override
    public CoreDTO update(CoreDTO updatedEntry) {
        Core core = repository.findOne(updatedEntry.getId());

        core.update(updatedEntry.getCoreName());

        return convertToDto(core);
    }
}
