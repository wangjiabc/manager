package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[select]")
public class Select {
    @Column(name = "[line_uuid]")
    private String lineUuid;

    @Column(name = "[name]")
    private String name;

    @Column(name = "[value]")
    private Integer value;

    /**
     * @return line_uuid
     */
    public String getLineUuid() {
        return lineUuid;
    }

    /**
     * @param lineUuid
     */
    public void setLineUuid(String lineUuid) {
        this.lineUuid = lineUuid == null ? null : lineUuid.trim();
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
     * @return value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(Integer value) {
        this.value = value;
    }
}