package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[electronic_file]")
public class ElectronicFile {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[name]")
    private String name;

    @Column(name = "[level]")
    private Integer level;

    @Column(name = "[parent_guid]")
    private String parentGuid;

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

    /**
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return parent_guid
     */
    public String getParentGuid() {
        return parentGuid;
    }

    /**
     * @param parentGuid
     */
    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid == null ? null : parentGuid.trim();
    }
}