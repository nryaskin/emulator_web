package com.niri.emulator.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niri.emulator.data.entity.Profile;

import javax.validation.constraints.Size;
import java.util.List;

public class ProfileDTO {
    private Long id;

    @Size(max = Profile.MAX_LENGTH_PROFILE_NAME)
    private String name;

    private CoreDTO core;

    @JsonIgnore
    private List<KeyMapDTO> keyMaps;

    public ProfileDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoreDTO getCore() {
        return core;
    }

    public void setCore(CoreDTO core) {
        this.core = core;
    }

    public List<KeyMapDTO> getKeyMaps() {
        return keyMaps;
    }

    public void setKeyMaps(List<KeyMapDTO> keyMaps) {
        this.keyMaps = keyMaps;
    }
}
