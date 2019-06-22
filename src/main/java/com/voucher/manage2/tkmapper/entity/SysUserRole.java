package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[sys_user_role]")
public class SysUserRole {
    @Column(name = "[role_guid]")
    private String roleGuid;

    @Column(name = "[user_guid]")
    private String userGuid;

    /**
     * @return role_guid
     */
    public String getRoleGuid() {
        return roleGuid;
    }

    /**
     * @param roleGuid
     */
    public void setRoleGuid(String roleGuid) {
        this.roleGuid = roleGuid == null ? null : roleGuid.trim();
    }

    /**
     * @return user_guid
     */
    public String getUserGuid() {
        return userGuid;
    }

    /**
     * @param userGuid
     */
    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid == null ? null : userGuid.trim();
    }
}