package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[sys_url]")
public class SysUrl {
    @Column(name = "[url]")
    private String url;

    @Column(name = "[name]")
    private String name;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}