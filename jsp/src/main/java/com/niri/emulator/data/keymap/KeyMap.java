package com.niri.emulator.data.keymap;

import com.niri.emulator.data.coreinput.CoreInput;
import com.niri.emulator.data.profile.Profile;

import javax.persistence.*;

@Entity
@Table(name = "key_maps", uniqueConstraints={
        @UniqueConstraint(columnNames = {"profile_id", "keyboard_value"})})
final public class KeyMap {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "keyboard_value")
    private String keyboardValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "input_key_id")
    private CoreInput coreInput;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public KeyMap() {}

    public String getKeyboardValue() {
        return keyboardValue;
    }

    public void setKeyboardValue(String keyboardValue) {
        this.keyboardValue = keyboardValue;
    }

    public CoreInput getCoreInput() {
        return coreInput;
    }

    public void setCoreInput(CoreInput coreInput) {
        this.coreInput = coreInput;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
