package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[menu]")
public class Menu {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[name]")
    private String name;

    @Column(name = "[level]")
    private Integer level;

    @Column(name = "[parent_guid]")
    private String parentGuid;

    @Column(name = "[root_guid]")
    private String rootGuid;

    @Column(name = "[required]")
    private Boolean required;

    @Column(name = "[del]")
    private Boolean del;

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

    /**
     * @return root_guid
     */
    public String getRootGuid() {
        return rootGuid;
    }

    /**
     * @param rootGuid
     */
    public void setRootGuid(String rootGuid) {
        this.rootGuid = rootGuid == null ? null : rootGuid.trim();
    }

    /**
     * @return required
     */
    public Boolean getRequired() {
        return required;
    }

    /**
     * @param required
     */
    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}