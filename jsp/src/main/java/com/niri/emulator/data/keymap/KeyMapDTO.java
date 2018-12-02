package com.niri.emulator.data.keymap;

import com.niri.emulator.data.coreinput.CoreInputDTO;
import com.niri.emulator.data.profile.ProfileDTO;

public class KeyMapDTO {
    private Long id;


    private String keyboardValue;

    private CoreInputDTO coreInput;

    private ProfileDTO profile;

    public KeyMapDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyboardValue() {
        return keyboardValue;
    }

    public void setKeyboardValue(String keyboardValue) {
        this.keyboardValue = keyboardValue;
    }

    public CoreInputDTO getCoreInput() {
        return coreInput;
    }

    public void setCoreInput(CoreInputDTO coreInput) {
        this.coreInput = coreInput;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }
}
