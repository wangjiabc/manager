package com.voucher.manage.daoModel;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

import javax.persistence.Column;

@DBTable(name = "[table_alias]")
public class Table_alias implements Serializable {

    private static final long serialVersionUID = 1L;

    @SQLInteger(name = "id")
    private Integer id;

    @SQLString(name = "table_name")
    private String table_name;

    @SQLString(name = "line_alias")
    private String line_alias;

    @SQLString(name = "line_uuid")
    private String line_uuid;

    @SQLLong(name = "date")
    private Long date;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @SQLBoolean(name = "del")
    private Boolean del;

    @SQLInteger(name = "type")
    private Integer type;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setLine_alias(String line_alias) {
        this.line_alias = line_alias;
    }

    public String getLine_alias() {
        return line_alias;
    }

    public void setLine_uuid(String line_uuid) {
        this.line_uuid = line_uuid;
    }

    public String getLine_uuid() {
        return line_uuid;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getDate() {
        return date;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public Boolean getDel() {
        return del;
    }


    /*
     *数据库查询参数
     */
    @QualifiLimit(name = "limit")
    private Integer limit;
    @QualifiOffset(name = "offset")
    private Integer offset;
    @QualifiNotIn(name = "notIn")
    private String notIn;
    @QualifiSort(name = "sort")
    private String sort;
    @QualifiOrder(name = "order")
    private String order;
    @QualifiWhere(name = "where")
    private String[] where;
    @QualifiWhereTerm(name = "whereTerm")
    private String whereTerm;


    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setNotIn(String notIn) {
        this.notIn = notIn;
    }

    public String getNotIn() {
        return notIn;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void setWhere(String[] where) {
        this.where = where;
    }

    public String[] getWhere() {
        return where;
    }

    public void setWhereTerm(String whereTerm) {
        this.whereTerm = whereTerm;
    }

    public String getWhereTerm() {
        return whereTerm;
    }

}

