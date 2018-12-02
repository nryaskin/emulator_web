package com.niri.emulator.data.core;

import com.niri.emulator.data.util.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

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

        return null;
    }

    @Transactional
    @Override
    public CoreDTO delete(Long id) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CoreDTO> findAll() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public CoreDTO findById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public CoreDTO update(CoreDTO updatedEntry) {
        return null;
    }
}
