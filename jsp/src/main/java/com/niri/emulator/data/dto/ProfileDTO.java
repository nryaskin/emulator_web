package com.niri.emulator.data.dto;

import com.niri.emulator.data.entity.Profile;

import javax.validation.constraints.Size;
import java.util.List;

public class ProfileDTO {
    private Long id;

    @Size(max = Profile.MAX_LENGTH_PROFILE_NAME)
    private String profileName;


    private CoreDTO core;

    private List<KeyMapDTO> keyMaps;

    public ProfileDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
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
