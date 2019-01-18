package com.niri.emulator.data.controller;

import com.niri.emulator.data.dto.ProfileDTO;
import com.niri.emulator.data.util.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("profile")
public class ProfileRestController {
    private CrudService<ProfileDTO> profileDTOCrudService;


    @Autowired
    public void setProfileDTOCrudService(CrudService<ProfileDTO> profileDTOCrudService) {
        this.profileDTOCrudService = profileDTOCrudService;
    }

    @GetMapping("/all")
    public List<ProfileDTO> getAllCores() {
        return profileDTOCrudService.findAll();
    }

    @GetMapping()
    public Page<ProfileDTO> getProfilePage(@RequestParam("page") int page,
                                           @RequestParam("size") int size) {
        return profileDTOCrudService.findPaginated(page, size);
    }

    @GetMapping("/{id}")
    public ProfileDTO getCore(@PathVariable long id) {
        Optional<ProfileDTO> profileDTO = profileDTOCrudService.findById(id);
        return profileDTO.orElse(null);
    }

    @PutMapping("/{id}")
    public ProfileDTO updateProfile(@RequestBody ProfileDTO profile) {
        Optional<ProfileDTO> updated =  profileDTOCrudService.update(profile);

        return updated.orElse(null);
    }

    @PostMapping()
    public ProfileDTO createProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO created = profileDTOCrudService.create(profileDTO);

        return created;
    }

    @DeleteMapping("/{id}")
    public void deleteCore(@PathVariable long id) {
        profileDTOCrudService.delete(id);
    }
}
