package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[room_out]")
public class RoomOut {
    @Column(name = "[room_guid]")
    private String roomGuid;

    @Column(name = "[date]")
    private Long date;

    @Column(name = "[destination]")
    private String destination;

    @Column(name = "[money]")
    private Double money;

    @Column(name = "[remark]")
    private String remark;

    @Column(name = "[type_guid]")
    private String typeGuid;

    @Column(name = "[guid]")
    private String guid;

    /**
     * @return room_guid
     */
    public String getRoomGuid() {
        return roomGuid;
    }

    /**
     * @param roomGuid
     */
    public void setRoomGuid(String roomGuid) {
        this.roomGuid = roomGuid == null ? null : roomGuid.trim();
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
     * @return destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination
     */
    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    /**
     * @return money
     */
    public Double getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return type_guid
     */
    public String getTypeGuid() {
        return typeGuid;
    }

    /**
     * @param typeGuid
     */
    public void setTypeGuid(String typeGuid) {
        this.typeGuid = typeGuid == null ? null : typeGuid.trim();
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
}