package com.niri.emulator.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profiles")
final public class Profile {

    public static final int MAX_LENGTH_PROFILE_NAME = 40;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "profile_name", length = MAX_LENGTH_PROFILE_NAME)
    private String profileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "core_id")
    private Core core;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
    private List<KeyMap> keyMaps;

    public Profile() {}

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Core getCore() {
        return core;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    public List<KeyMap> getKeyMaps() {
        return keyMaps;
    }

    public void setKeyMaps(List<KeyMap> keyMaps) {
        this.keyMaps = keyMaps;
    }
}
