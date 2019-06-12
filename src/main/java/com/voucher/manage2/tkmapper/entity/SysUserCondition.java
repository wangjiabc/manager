package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[sys_user_condition]")
public class SysUserCondition {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[user_guid]")
    private String userGuid;

    @Column(name = "[line_uuid]")
    private String lineUuid;

    @Column(name = "[line_value]")
    private Integer lineValue;

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
     * @return user_guid
     */
    public String getUserGuid() {
        return userGuid;
    }

    /**
     * @param userGuid
     */
    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid == null ? null : userGuid.trim();
    }

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
     * @return line_value
     */
    public Integer getLineValue() {
        return lineValue;
    }

    /**
     * @param lineValue
     */
    public void setLineValue(Integer lineValue) {
        this.lineValue = lineValue;
    }
}