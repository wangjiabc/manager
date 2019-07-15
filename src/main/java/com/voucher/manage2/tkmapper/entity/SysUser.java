package com.voucher.manage2.tkmapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "[sys_user]")
public class SysUser implements Serializable {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[account_name]")
    private String accountName;

    @Column(name = "[name]")
    private String name;

    @Column(name = "[phone]")
    private String phone;

    @Column(name = "[email]")
    private String email;

    @Column(name = "[password]")
    private String password;

    @Column(name = "[salt]")
    private String salt;

    @Column(name = "[del]")
    private Boolean del;

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
     * @return account_name
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
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

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
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