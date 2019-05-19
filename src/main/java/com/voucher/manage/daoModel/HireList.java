package com.voucher.manage.daoModel;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[HireList]")
public class HireList implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
	private Integer id;

    @SQLString(name="HireGUID")
	private String HireGUID;

    @SQLString(name="ChartGUID")
	private String ChartGUID;

    @SQLString(name="HirePayGUID")
	private String HirePayGUID;

    @SQLDateTime(name="HireDate")
	private Date HireDate;

    @SQLFloat(name="Hire")
	private Float Hire;

    @SQLBoolean(name="State")
	private Boolean State;

    @SQLString(name="Operator")
	private String Operator;

    @SQLString(name="open_id")
	private String open_id;

    @SQLDateTime(name="repayDate")
	private Date repayDate;

    @SQLDateTime(name="OptDate")
	private Date OptDate;

    @SQLBoolean(name="IsAddHire")
	private Boolean IsAddHire;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setHireGUID(String HireGUID){
		this.HireGUID = HireGUID;
	}

	public String getHireGUID(){
		return HireGUID;
	}

	public void setChartGUID(String ChartGUID){
		this.ChartGUID = ChartGUID;
	}

	public String getChartGUID(){
		return ChartGUID;
	}

	public void setHirePayGUID(String HirePayGUID){
		this.HirePayGUID = HirePayGUID;
	}

	public String getHirePayGUID(){
		return HirePayGUID;
	}

	public void setHireDate(Date HireDate){
		this.HireDate = HireDate;
	}

	public Date getHireDate(){
		return HireDate;
	}

	public void setHire(Float Hire){
		this.Hire = Hire;
	}

	public Float getHire(){
		return Hire;
	}

	public void setState(Boolean State){
		this.State = State;
	}

	public Boolean getState(){
		return State;
	}

	public void setOperator(String Operator){
		this.Operator = Operator;
	}

	public String getOperator(){
		return Operator;
	}

	public void setOpen_id(String open_id){
		this.open_id = open_id;
	}

	public String getOpen_id(){
		return open_id;
	}

	public void setRepayDate(Date repayDate){
		this.repayDate = repayDate;
	}

	public Date getRepayDate(){
		return repayDate;
	}

	public void setOptDate(Date OptDate){
		this.OptDate = OptDate;
	}

	public Date getOptDate(){
		return OptDate;
	}

	public void setIsAddHire(Boolean IsAddHire){
		this.IsAddHire = IsAddHire;
	}

	public Boolean getIsAddHire(){
		return IsAddHire;
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

