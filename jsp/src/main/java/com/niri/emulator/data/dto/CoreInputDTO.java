package com.niri.emulator.data.dto;

import com.niri.emulator.data.entity.CoreInput;

import javax.validation.constraints.Size;

public class CoreInputDTO {
    private Long id;

    private CoreDTO core;

    private Long key;

    @Size(max = CoreInput.MAX_LENGTH_ACTION_NAME)
    private String actionName;

    public CoreInputDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoreDTO getCore() {
        return core;
    }

    public void setCore(CoreDTO core) {
        this.core = core;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
