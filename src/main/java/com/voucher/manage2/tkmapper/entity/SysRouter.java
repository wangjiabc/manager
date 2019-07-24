package com.voucher.manage2.tkmapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "[sys_router]")
public class SysRouter implements Serializable {
    @Column(name = "[path]")
    private String path;

    @Column(name = "[component]")
    private String component;

    @Column(name = "[name]")
    private String name;

    @Column(name = "[title]")
    private String title;

    @Column(name = "[icon]")
    private String icon;

    @Column(name = "[level]")
    private Integer level;

    @Column(name = "[parent_guid]")
    private String parentGuid;

    @Column(name = "[root_guid]")
    private String rootGuid;

    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[hidden]")
    private Boolean hidden;

    @Column(name = "[noCache]")
    private Boolean nocache;

    private static final long serialVersionUID = 1L;

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * @return component
     */
    public String getComponent() {
        return component;
    }

    /**
     * @param component
     */
    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
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
     * @return hidden
     */
    public Boolean getHidden() {
        return hidden;
    }

    /**
     * @param hidden
     */
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * @return noCache
     */
    public Boolean getNocache() {
        return nocache;
    }

    /**
     * @param nocache
     */
    public void setNocache(Boolean nocache) {
        this.nocache = nocache;
    }
}