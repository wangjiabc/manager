package com.voucher.manage2.tkmapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "[room]")
public class Room implements Serializable {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[num]")
    private String num;

    @Column(name = "[address]")
    private String address;

    @Column(name = "[build_area]")
    private Double buildArea;

    @Column(name = "[region]")
    private String region;

    @Column(name = "[manage_region]")
    private String manageRegion;

    @Column(name = "[room_property]")
    private Integer roomProperty;

    @Column(name = "[state]")
    private Integer state;

    @Column(name = "[hidden_check_date]")
    private Long hiddenCheckDate;

    @Column(name = "[asset_check_date]")
    private Long assetCheckDate;

    @Column(name = "[hidden]")
    private Integer hidden;

    @Column(name = "[neaten_flow]")
    private Integer neatenFlow;

    @Column(name = "[del]")
    private Boolean del;

    @Column(name = "[in_date]")
    private Long inDate;

    @Column(name = "[in_type]")
    private String inType;

    @Column(name = "[company_guid]")
    private String companyGuid;

    private static final long serialVersionUID = 1L;

    /**
     * @return guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * @return num
     */
    public String getNum() {
        return num;
    }

    /**
     * @param num
     */
    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return build_area
     */
    public Double getBuildArea() {
        return buildArea;
    }

    /**
     * @param buildArea
     */
    public void setBuildArea(Double buildArea) {
        this.buildArea = buildArea;
    }

    /**
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region
     */
    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    /**
     * @return manage_region
     */
    public String getManageRegion() {
        return manageRegion;
    }

    /**
     * @param manageRegion
     */
    public void setManageRegion(String manageRegion) {
        this.manageRegion = manageRegion == null ? null : manageRegion.trim();
    }

    /**
     * @return room_property
     */
    public Integer getRoomProperty() {
        return roomProperty;
    }

    /**
     * @param roomProperty
     */
    public void setRoomProperty(Integer roomProperty) {
        this.roomProperty = roomProperty;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return hidden_check_date
     */
    public Long getHiddenCheckDate() {
        return hiddenCheckDate;
    }

    /**
     * @param hiddenCheckDate
     */
    public void setHiddenCheckDate(Long hiddenCheckDate) {
        this.hiddenCheckDate = hiddenCheckDate;
    }

    /**
     * @return asset_check_date
     */
    public Long getAssetCheckDate() {
        return assetCheckDate;
    }

    /**
     * @param assetCheckDate
     */
    public void setAssetCheckDate(Long assetCheckDate) {
        this.assetCheckDate = assetCheckDate;
    }

    /**
     * @return hidden
     */
    public Integer getHidden() {
        return hidden;
    }

    /**
     * @param hidden
     */
    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    /**
     * @return neaten_flow
     */
    public Integer getNeatenFlow() {
        return neatenFlow;
    }

    /**
     * @param neatenFlow
     */
    public void setNeatenFlow(Integer neatenFlow) {
        this.neatenFlow = neatenFlow;
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
     * @return in_date
     */
    public Long getInDate() {
        return inDate;
    }

    /**
     * @param inDate
     */
    public void setInDate(Long inDate) {
        this.inDate = inDate;
    }

    /**
     * @return in_type
     */
    public String getInType() {
        return inType;
    }

    /**
     * @param inType
     */
    public void setInType(String inType) {
        this.inType = inType == null ? null : inType.trim();
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