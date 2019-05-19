package com.voucher.manage.daoModelJoin;

import java.util.Date;

import com.voucher.manage.daoSQL.annotations.SQLBoolean;
import com.voucher.manage.daoSQL.annotations.SQLDateTime;
import com.voucher.manage.daoSQL.annotations.SQLFloat;
import com.voucher.manage.daoSQL.annotations.SQLInteger;
import com.voucher.manage.daoSQL.annotations.SQLString;

public class ChartInfo_ChartRoom {


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

    @SQLFloat(name="ChartArea")
	private Float ChartArea;

    @SQLInteger(name="ChartMothon")
	private Integer ChartMothon;

    @SQLFloat(name="AllHire")
	private Float AllHire;

    @SQLFloat(name="AllTotalHire")
	private Float AllTotalHire;

    @SQLBoolean(name="IsHistory")
	private Boolean IsHistory;

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

	public void setChartArea(Float ChartArea){
		this.ChartArea = ChartArea;
	}

	public Float getChartArea(){
		return ChartArea;
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

	public void setIsHistory(Boolean IsHistory){
		this.IsHistory = IsHistory;
	}

	public Boolean getIsHistory(){
		return IsHistory;
	}
	

	    @SQLString(name="guid")
		private String guid;

	    @SQLString(name="Charter")
		private String Charter;

	    @SQLBoolean(name="Sex")
		private Boolean Sex;

	    @SQLString(name="CardType")
		private String CardType;

	    @SQLString(name="IDNo")
		private String IDNo;

	    @SQLString(name="Phone")
		private String Phone;


		public void setGuid(String guid){
			this.guid = guid;
		}

		public String getGuid(){
			return guid;
		}

		public void setCharter(String Charter){
			this.Charter = Charter;
		}

		public String getCharter(){
			return Charter;
		}

		public void setSex(Boolean Sex){
			this.Sex = Sex;
		}

		public Boolean getSex(){
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

	
}
