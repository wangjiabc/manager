package com.voucher.manage2.tkmapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "[company]")
public class Company implements Serializable {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[name]")
    private String name;

    @Column(name = "[type]")
    private Integer type;

    @Column(name = "[scale]")
    private Integer scale;

    @Column(name = "[create_time]")
    private Long createTime;

    private static final long serialVersionUID = 1L;

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
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return scale
     */
    public Integer getScale() {
        return scale;
    }

    /**
     * @param scale
     */
    public void setScale(Integer scale) {
        this.scale = scale;
    }

    /**
     * @return create_time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}