package com.niri.emulator.data.core;

import com.niri.emulator.data.coreinput.CoreInput;
import com.niri.emulator.data.profile.Profile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cores")
final public class Core {

    public static final int MAX_LENGTH_CORE_NAME = 40;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "core_name", length = MAX_LENGTH_CORE_NAME)
    private String coreName;

    @OneToMany(mappedBy = "core")
    private List<Profile> profiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "core")
    private List<CoreInput> coreInput;

    public Core() {}

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

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<CoreInput> getCoreInput() {
        return coreInput;
    }

    public void setCoreInput(List<CoreInput> coreInput) {
        this.coreInput = coreInput;
    }
}
