package com.voucher.manage.daoModel;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[room_log]")
public class Room_log implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
	private Integer id;

    @SQLString(name="guid")
	private String guid;

    @SQLString(name="operation_guid")
	private String operation_guid;

    @SQLString(name="introduction")
	private String introduction;

    @SQLString(name="user_guid")
	private String user_guid;

    @SQLLong(name="date")
	private Long date;

    @SQLString(name="operation_type")
	private String operation_type;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setGuid(String guid){
		this.guid = guid;
	}

	public String getGuid(){
		return guid;
	}

	public void setOperation_guid(String operation_guid){
		this.operation_guid = operation_guid;
	}

	public String getOperation_guid(){
		return operation_guid;
	}

	public void setIntroduction(String introduction){
		this.introduction = introduction;
	}

	public String getIntroduction(){
		return introduction;
	}

	public void setUser_guid(String user_guid){
		this.user_guid = user_guid;
	}

	public String getUser_guid(){
		return user_guid;
	}

	public void setDate(Long date){
		this.date = date;
	}

	public Long getDate(){
		return date;
	}

	public void setOperation_type(String operation_type){
		this.operation_type = operation_type;
	}

	public String getOperation_type(){
		return operation_type;
	}




/*
*数据库查询参数
*/
    @QualifiLimit(name="limit")
    private Integer limit;
    @QualifiOffset(name="offset")
    private Integer offset;
    @QualifiNotIn(name="notIn")
    private String notIn;
    @QualifiSort(name="sort")
    private String sort;
    @QualifiOrder(name="order")
    private String order;
    @QualifiWhere(name="where")
    private String[] where;
    @QualifiWhereTerm(name="whereTerm")
    private String whereTerm;


	public void setLimit(Integer limit){
		this.limit = limit;
	}

	public Integer getLimit(){
		return limit;
	}

	public void setOffset(Integer offset){
		this.offset = offset;
	}

	public Integer getOffset(){
		return offset;
	}

	public void setNotIn(String notIn){
		this.notIn = notIn;
	}

	public String getNotIn(){
		return notIn;
	}

	public void setSort(String sort){
		this.sort = sort;
	}

	public String getSort(){
		return sort;
	}

	public void setOrder(String order){
		this.order = order;
	}

	public String getOrder(){
		return order;
	}

	public void setWhere(String[] where){
		this.where = where;
	}

	public String[] getWhere(){
		return where;
	}

	public void setWhereTerm(String whereTerm){
		this.whereTerm = whereTerm;
	}

	public String getWhereTerm(){
		return whereTerm;
	}

}

