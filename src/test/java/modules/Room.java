package modules;

import java.io.Serializable;

import com.voucher.crab2died.annotation.ExcelField;
import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[room]")
public class Room implements Serializable{

    private static final long serialVersionUID = 1L;

    @ExcelField(title = "id", order = 1)
	private Integer id;

    @ExcelField(title = "guid", order = 2)
	private String guid;

    @ExcelField(title = "num", order = 3)
	private String num;

    @ExcelField(title = "address", order = 4)
	private String address;

    @ExcelField(title = "build_area", order = 5)
	private Float build_area;

    @ExcelField(title = "region", order = 6)
	private String region;

    @ExcelField(title = "manage_region", order = 7)
	private String manage_region;

    @ExcelField(title = "room_property", order = 8)
	private Integer room_property;

    @ExcelField(title = "state", order = 9)
	private Integer state;

    @ExcelField(title = "hidden_check_date", order = 10)
	private Long hidden_check_date;

    @ExcelField(title = "asset_check_date", order = 11)
	private Long asset_check_date;

    @ExcelField(title = "hidden", order = 12)
	private Integer hidden;

    @ExcelField(title = "neaten_flow", order = 13)
	private Integer neaten_flow;

    @ExcelField(title = "del", order = 14)
	private Boolean del;

    @ExcelField(title = "in_date", order = 15)
	private Long in_date;

    @ExcelField(title = "in_type", order = 16)
	private String in_type;

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

	public void setNum(String num){
		this.num = num;
	}

	public String getNum(){
		return num;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setBuild_area(Float build_area){
		this.build_area = build_area;
	}

	public Float getBuild_area(){
		return build_area;
	}

	public void setRegion(String region){
		this.region = region;
	}

	public String getRegion(){
		return region;
	}

	public void setManage_region(String manage_region){
		this.manage_region = manage_region;
	}

	public String getManage_region(){
		return manage_region;
	}

	public void setRoom_property(Integer room_property){
		this.room_property = room_property;
	}

	public Integer getRoom_property(){
		return room_property;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return state;
	}

	public void setHidden_check_date(Long hidden_check_date){
		this.hidden_check_date = hidden_check_date;
	}

	public Long getHidden_check_date(){
		return hidden_check_date;
	}

	public void setAsset_check_date(Long asset_check_date){
		this.asset_check_date = asset_check_date;
	}

	public Long getAsset_check_date(){
		return asset_check_date;
	}

	public void setHidden(Integer hidden){
		this.hidden = hidden;
	}

	public Integer getHidden(){
		return hidden;
	}

	public void setNeaten_flow(Integer neaten_flow){
		this.neaten_flow = neaten_flow;
	}

	public Integer getNeaten_flow(){
		return neaten_flow;
	}

	public void setDel(Boolean del){
		this.del = del;
	}

	public Boolean getDel(){
		return del;
	}

	public void setIn_date(Long in_date){
		this.in_date = in_date;
	}

	public Long getIn_date(){
		return in_date;
	}

	public void setIn_type(String in_type){
		this.in_type = in_type;
	}

	public String getIn_type(){
		return in_type;
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

