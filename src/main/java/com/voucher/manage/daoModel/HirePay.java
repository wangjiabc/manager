package com.voucher.manage.daoModel;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[HirePay]")
public class HirePay implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
	private Integer id;

    @SQLString(name="HirePayGUID")
	private String HirePayGUID;

    @SQLString(name="ChartGUID")
	private String ChartGUID;

    @SQLFloat(name="Amount")
	private Float Amount;

    @SQLString(name="Operator")
	private String Operator;

    @SQLString(name="open_id")
	private String open_id;

    @SQLDateTime(name="OptDate")
	private Date OptDate;

    @SQLInteger(name="del")
	private Integer del;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setHirePayGUID(String HirePayGUID){
		this.HirePayGUID = HirePayGUID;
	}

	public String getHirePayGUID(){
		return HirePayGUID;
	}

	public void setChartGUID(String ChartGUID){
		this.ChartGUID = ChartGUID;
	}

	public String getChartGUID(){
		return ChartGUID;
	}

	public void setAmount(Float Amount){
		this.Amount = Amount;
	}

	public Float getAmount(){
		return Amount;
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

	public void setOptDate(Date OptDate){
		this.OptDate = OptDate;
	}

	public Date getOptDate(){
		return OptDate;
	}

	public void setDel(Integer del){
		this.del = del;
	}

	public Integer getDel(){
		return del;
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

