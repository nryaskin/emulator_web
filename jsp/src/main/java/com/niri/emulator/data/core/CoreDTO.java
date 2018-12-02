package com.niri.emulator.data.core;

import com.niri.emulator.data.coreinput.CoreInputDTO;
import com.niri.emulator.data.profile.ProfileDTO;

import javax.validation.constraints.Size;
import java.util.List;

public final class CoreDTO {
    private Long id;

    @Size(max = Core.MAX_LENGTH_CORE_NAME)
    private String coreName;


    private List<ProfileDTO> profiles;

    private List<CoreInputDTO> coreInput;

    public CoreDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    public List<ProfileDTO> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ProfileDTO> profiles) {
        this.profiles = profiles;
    }

    public List<CoreInputDTO> getCoreInput() {
        return coreInput;
    }

    public void setCoreInput(List<CoreInputDTO> coreInput) {
        this.coreInput = coreInput;
    }
}
