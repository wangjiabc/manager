package com.voucher.manage2.tkmapper.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "[upload_file]")
public class UploadFile {
    @Column(name = "[type]")
    private Integer type;

    @Column(name = "[upload_time]")
    private Long uploadTime;

    @Column(name = "[url]")
    private String url;

    @Column(name = "[guid]")
    private String guid;

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return upload_time
     */
    public Long getUploadTime() {
        return uploadTime;
    }

    /**
     * @param uploadTime
     */
    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

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
}