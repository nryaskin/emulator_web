package com.niri.emulator.data.controller;

import com.niri.emulator.data.dto.ProfileDTO;
import com.niri.emulator.data.util.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("profile")
public class ProfileRestController {
    private CrudService<ProfileDTO> profileDTOCrudService;


    @Autowired
    public void setProfileDTOCrudService(CrudService<ProfileDTO> profileDTOCrudService) {
        this.profileDTOCrudService = profileDTOCrudService;
    }

    @GetMapping
    public List<ProfileDTO> getAllCores() {
        return profileDTOCrudService.findAll();
    }

    @GetMapping("/{id}")
    public ProfileDTO getCore(@PathVariable long id) {
        Optional<ProfileDTO> profileDTO = profileDTOCrudService.findById(id);
        return profileDTO.orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfile(@RequestBody ProfileDTO profile) {
        Optional<ProfileDTO> updated =  profileDTOCrudService.update(profile);

        if (!updated.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<Object> createCore(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO created = profileDTOCrudService.create(profileDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteCore(@PathVariable long id) {
        profileDTOCrudService.delete(id);
    }
}
