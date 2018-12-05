package com.niri.emulator.data.core;

import com.niri.emulator.data.coreinput.CoreInput;
import com.niri.emulator.data.profile.Profile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cores")
final public class Core {


    public static final int MAX_LENGTH_CORE_NAME = 40;
    public static final int MAX_LENGTH_CORE_PATH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="core_id")
    private Long id;

    @Column(name = "core_name", length = MAX_LENGTH_CORE_NAME)
    private String coreName;

    @Column(name = "core_path", length = MAX_LENGTH_CORE_PATH)
    private String path;

    @OneToMany(mappedBy = "core")
    private List<Profile> profiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "core", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public void update(String coreName) {
        this.coreName = coreName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
