package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[room_in]")
public class RoomIn {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[date]")
    private Long date;

    @Column(name = "[source]")
    private String source;

    @Column(name = "[money]")
    private Double money;

    @Column(name = "[remark]")
    private String remark;

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
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
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
}