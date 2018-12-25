package com.niri.emulator.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.niri.emulator.data.entity.Core;

import javax.validation.constraints.Size;
import java.util.List;

public final class CoreDTO {
    private Long id;

    @JsonProperty("coreName")
    @Size(max = Core.MAX_LENGTH_CORE_NAME)
    private String coreName;

    @JsonProperty("corePath")
    @Size(max = Core.MAX_LENGTH_CORE_PATH)
    private String corePath;

    //private List<ProfileDTO> profiles;

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

    //public List<ProfileDTO> getProfiles() {
    //    return profiles;
    //}

    //public void setProfiles(List<ProfileDTO> profiles) {
    //    this.profiles = profiles;
    //}

    public List<CoreInputDTO> getCoreInput() {
        return coreInput;
    }

    public void setCoreInput(List<CoreInputDTO> coreInput) {
        this.coreInput = coreInput;
    }

    public String getCorePath() {
        return corePath;
    }

    public void setCorePath(String corePath) {
        this.corePath = corePath;
    }
}
