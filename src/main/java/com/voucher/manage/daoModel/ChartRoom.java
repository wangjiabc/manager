package com.voucher.manage.daoModel;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[ChartRoom]")
public class ChartRoom implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
	private Integer id;

    @SQLString(name="ChartGUID")
	private String ChartGUID;

    @SQLString(name="guid")
	private String guid;

    @SQLFloat(name="ChartArea")
	private Float ChartArea;

    @SQLFloat(name="hire")
	private Float hire;

    @SQLString(name="Charter")
	private String Charter;

    @SQLInteger(name="Sex")
	private Integer Sex;

    @SQLString(name="CardType")
	private String CardType;

    @SQLString(name="IDNo")
	private String IDNo;

    @SQLString(name="Phone")
	private String Phone;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setChartGUID(String ChartGUID){
		this.ChartGUID = ChartGUID;
	}

	public String getChartGUID(){
		return ChartGUID;
	}

	public void setGuid(String guid){
		this.guid = guid;
	}

	public String getGuid(){
		return guid;
	}

	public void setChartArea(Float ChartArea){
		this.ChartArea = ChartArea;
	}

	public Float getChartArea(){
		return ChartArea;
	}

	public void setHire(Float hire){
		this.hire = hire;
	}

	public Float getHire(){
		return hire;
	}

	public void setCharter(String Charter){
		this.Charter = Charter;
	}

	public String getCharter(){
		return Charter;
	}

	public void setSex(Integer Sex){
		this.Sex = Sex;
	}

	public Integer getSex(){
		return Sex;
	}

	public void setCardType(String CardType){
		this.CardType = CardType;
	}

	public String getCardType(){
		return CardType;
	}

	public void setIDNo(String IDNo){
		this.IDNo = IDNo;
	}

	public String getIDNo(){
		return IDNo;
	}

	public void setPhone(String Phone){
		this.Phone = Phone;
	}

	public String getPhone(){
		return Phone;
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

