package com.niri.emulator.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "core")
public class Core {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "core")
    private List<Profile> profiles;

    public Core() {}
}
