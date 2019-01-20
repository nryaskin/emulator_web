package com.niri.emulator.data.controller;

import com.niri.emulator.data.dto.ProfileDTO;
import com.niri.emulator.data.util.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
                                           @RequestParam("size") int size,
                                           @RequestParam("sortby")String sortBy,
                                           @RequestParam("order")String order){
        Sort sort;
        switch(sortBy) {
            case "name":
                sort = Sort.by(sortBy);
                break;
            case "core_name":
                sort = Sort.by("core.core_name");
                break;
            case "unsorted":
                sort = Sort.unsorted();
                break;
            default: throw new NotImplementedException();
        }
        switch(order) {
            case "desc":
                sort = sort.descending();
                break;
            default:
                sort = sort.ascending();
                break;
        }
        return profileDTOCrudService.findPaginated(page, size, sort);
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
