package com.niri.emulator.data.coreinput;

import com.niri.emulator.data.core.Core;

import javax.persistence.*;

@Entity
@Table(name = "core_inputs", uniqueConstraints={
        @UniqueConstraint(columnNames = {"core_id", "key_val"})})
final public class CoreInput {

    public static final int MAX_LENGTH_ACTION_NAME = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "core_id")
    private Core core;

    @Column(name = "key_val")
    private Long key;

    @Column(name = "action_name", length = MAX_LENGTH_ACTION_NAME)
    private String actionName;

    public CoreInput() {}

    public Core getCore() {
        return core;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    public Long getKey() {
        return key;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
