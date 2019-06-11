package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[sys_user_condition]")
public class SysUserCondition {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[user_guid]")
    private String userGuid;

    @Column(name = "[sql_condition]")
    private String sqlCondition;

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
     * @return sql_condition
     */
    public String getSqlCondition() {
        return sqlCondition;
    }

    /**
     * @param sqlCondition
     */
    public void setSqlCondition(String sqlCondition) {
        this.sqlCondition = sqlCondition == null ? null : sqlCondition.trim();
    }
}