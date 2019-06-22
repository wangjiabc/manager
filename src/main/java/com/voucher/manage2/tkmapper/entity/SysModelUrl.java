package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[sys_model_url]")
public class SysModelUrl {
    @Column(name = "[model_guid]")
    private String modelGuid;

    @Column(name = "[url]")
    private String url;

    /**
     * @return model_guid
     */
    public String getModelGuid() {
        return modelGuid;
    }

    /**
     * @param modelGuid
     */
    public void setModelGuid(String modelGuid) {
        this.modelGuid = modelGuid == null ? null : modelGuid.trim();
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
}