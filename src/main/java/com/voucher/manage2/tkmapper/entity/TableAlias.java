package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[table_alias]")
public class TableAlias {
    @Column(name = "[id]")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

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

    @Column(name = "[type]")
    private Byte type;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * @return type
     */
    public Byte getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Byte type) {
        this.type = type;
    }
}