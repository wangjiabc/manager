package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[sys_role_menu]")
public class SysRoleMenu {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[role_guid]")
    private String roleGuid;

    @Column(name = "[menu_guid]")
    private String menuGuid;

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
     * @return menu_guid
     */
    public String getMenuGuid() {
        return menuGuid;
    }

    /**
     * @param menuGuid
     */
    public void setMenuGuid(String menuGuid) {
        this.menuGuid = menuGuid == null ? null : menuGuid.trim();
    }
}