package com.voucher.manage2.tkmapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "[table_alias]")
public class TableAlias implements Serializable {
    @Column(name = "[table_name]")
    private String tableName;

    @Column(name = "[line_alias]")
    private String lineAlias;

    @Column(name = "[line_uuid]")
    private String lineUuid;

    @Column(name = "[date]")
    private Long date;

    @Column(name = "[del]")
    private Boolean del;

    @Column(name = "[row_type]")
    private Integer rowType;

    @Column(name = "[room_type]")
    private Integer roomType;

    @Column(name = "[company_guid]")
    private String companyGuid;

    private static final long serialVersionUID = 1L;

    /**
     * @return table_name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * @return line_alias
     */
    public String getLineAlias() {
        return lineAlias;
    }

    /**
     * @param lineAlias
     */
    public void setLineAlias(String lineAlias) {
        this.lineAlias = lineAlias == null ? null : lineAlias.trim();
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
     * @return del
     */
    public Boolean getDel() {
        return del;
    }

    /**
     * @param del
     */
    public void setDel(Boolean del) {
        this.del = del;
    }

    /**
     * @return row_type
     */
    public Integer getRowType() {
        return rowType;
    }

    /**
     * @param rowType
     */
    public void setRowType(Integer rowType) {
        this.rowType = rowType;
    }

    /**
     * @return room_type
     */
    public Integer getRoomType() {
        return roomType;
    }

    /**
     * @param roomType
     */
    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    /**
     * @return company_guid
     */
    public String getCompanyGuid() {
        return companyGuid;
    }

    /**
     * @param companyGuid
     */
    public void setCompanyGuid(String companyGuid) {
        this.companyGuid = companyGuid == null ? null : companyGuid.trim();
    }
}