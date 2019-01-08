package com.niri.emulator.data.controller;

import com.niri.emulator.data.dto.CoreDTO;
import com.niri.emulator.data.dto.CoreInputDTO;
import com.niri.emulator.data.util.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("core")
public class CoreRestController {

    private CrudService<CoreDTO> coreCrudService;

    @Autowired
    public void setCoreCrudService(CrudService<CoreDTO> coreCrudService) {
        this.coreCrudService = coreCrudService;
    }

    @GetMapping
    public List<CoreDTO> getAllCores() {
        return coreCrudService.findAll();
    }

    @GetMapping("/{id}")
    public CoreDTO getCore(@PathVariable long id) {
        Optional<CoreDTO> coreDTO = coreCrudService.findById(id);
        return coreDTO.orElse(null);
    }

    @PostMapping()
    public CoreDTO createCore(@RequestBody CoreDTO core) {
        CoreDTO created = coreCrudService.create(core);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(created.getId()).toUri();

        return created;
    }

    @PutMapping("/{id}")
    public CoreDTO updateCore(@RequestBody CoreDTO core) {
        Optional<CoreDTO> updated =  coreCrudService.update(core);

        /*
        if (!updated.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
        */
        return updated.orElse(null);
    }

    @DeleteMapping("/{id}")
    public CoreDTO deleteCore(@PathVariable long id) {
        return coreCrudService.delete(id).orElse(null);
    }

}
