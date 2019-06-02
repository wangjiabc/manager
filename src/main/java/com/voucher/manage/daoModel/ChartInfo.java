package com.voucher.manage.daoModel;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[ChartInfo]")
public class ChartInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
	private Integer id;

    @SQLString(name="ChartGUID")
	private String ChartGUID;

    @SQLString(name="ContractNo")
	private String ContractNo;

    @SQLDateTime(name="ConcludeDate")
	private Date ConcludeDate;

    @SQLDateTime(name="ChartBeginDate")
	private Date ChartBeginDate;

    @SQLDateTime(name="ChartEndDate")
	private Date ChartEndDate;

    @SQLFloat(name="AllChartArea")
	private Float AllChartArea;

    @SQLInteger(name="ChartMothon")
	private Integer ChartMothon;

    @SQLFloat(name="AllHire")
	private Float AllHire;

    @SQLFloat(name="AllTotalHire")
	private Float AllTotalHire;

    @SQLInteger(name="Augment")
	private Integer Augment;

    @SQLInteger(name="AugmentGenre")
	private Integer AugmentGenre;

    @SQLFloat(name="Increment")
	private Float Increment;

    @SQLDateTime(name="AugmentDate")
	private Date AugmentDate;

    @SQLInteger(name="IsHistory")
	private Integer IsHistory;

    @SQLInteger(name="IsRelet")
	private Integer IsRelet;

    @SQLString(name="ReletGUID")
	private String ReletGUID;

    @SQLInteger(name="del")
	private Integer del;

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

	public void setContractNo(String ContractNo){
		this.ContractNo = ContractNo;
	}

	public String getContractNo(){
		return ContractNo;
	}

	public void setConcludeDate(Date ConcludeDate){
		this.ConcludeDate = ConcludeDate;
	}

	public Date getConcludeDate(){
		return ConcludeDate;
	}

	public void setChartBeginDate(Date ChartBeginDate){
		this.ChartBeginDate = ChartBeginDate;
	}

	public Date getChartBeginDate(){
		return ChartBeginDate;
	}

	public void setChartEndDate(Date ChartEndDate){
		this.ChartEndDate = ChartEndDate;
	}

	public Date getChartEndDate(){
		return ChartEndDate;
	}

	public void setAllChartArea(Float AllChartArea){
		this.AllChartArea = AllChartArea;
	}

	public Float getAllChartArea(){
		return AllChartArea;
	}

	public void setChartMothon(Integer ChartMothon){
		this.ChartMothon = ChartMothon;
	}

	public Integer getChartMothon(){
		return ChartMothon;
	}

	public void setAllHire(Float AllHire){
		this.AllHire = AllHire;
	}

	public Float getAllHire(){
		return AllHire;
	}

	public void setAllTotalHire(Float AllTotalHire){
		this.AllTotalHire = AllTotalHire;
	}

	public Float getAllTotalHire(){
		return AllTotalHire;
	}

	public void setAugment(Integer Augment){
		this.Augment = Augment;
	}

	public Integer getAugment(){
		return Augment;
	}

	public void setAugmentGenre(Integer AugmentGenre){
		this.AugmentGenre = AugmentGenre;
	}

	public Integer getAugmentGenre(){
		return AugmentGenre;
	}

	public void setIncrement(Float Increment){
		this.Increment = Increment;
	}

	public Float getIncrement(){
		return Increment;
	}

	public void setAugmentDate(Date AugmentDate){
		this.AugmentDate = AugmentDate;
	}

	public Date getAugmentDate(){
		return AugmentDate;
	}

	public void setIsHistory(Integer IsHistory){
		this.IsHistory = IsHistory;
	}

	public Integer getIsHistory(){
		return IsHistory;
	}

	public void setIsRelet(Integer IsRelet){
		this.IsRelet = IsRelet;
	}

	public Integer getIsRelet(){
		return IsRelet;
	}

	public void setReletGUID(String ReletGUID){
		this.ReletGUID = ReletGUID;
	}

	public String getReletGUID(){
		return ReletGUID;
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

