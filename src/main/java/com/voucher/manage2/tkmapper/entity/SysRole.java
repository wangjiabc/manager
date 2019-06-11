package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[sys_role]")
public class SysRole {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[name]")
    private String name;

    /**
     * @return guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}