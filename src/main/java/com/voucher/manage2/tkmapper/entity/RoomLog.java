package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[room_log]")
public class RoomLog {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[operation_guid]")
    private String operationGuid;

    @Column(name = "[introduction]")
    private String introduction;

    @Column(name = "[user_guid]")
    private String userGuid;

    @Column(name = "[date]")
    private Long date;

    @Column(name = "[operation_type]")
    private String operationType;

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
     * @return operation_guid
     */
    public String getOperationGuid() {
        return operationGuid;
    }

    /**
     * @param operationGuid
     */
    public void setOperationGuid(String operationGuid) {
        this.operationGuid = operationGuid == null ? null : operationGuid.trim();
    }

    /**
     * @return introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
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
     * @return date
     */
    public Long getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Long date) {
        this.date = date;
    }

    /**
     * @return operation_type
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * @param operationType
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }
}