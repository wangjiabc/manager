package com.voucher.manage2.tkmapper.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "[sys_role_router]")
public class SysRoleRouter implements Serializable {
    @Column(name = "[guid]")
    private String guid;

    @Column(name = "[role_guid]")
    private String roleGuid;

    @Column(name = "[router_guid]")
    private String routerGuid;

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
     * @return router_guid
     */
    public String getRouterGuid() {
        return routerGuid;
    }

    /**
     * @param routerGuid
     */
    public void setRouterGuid(String routerGuid) {
        this.routerGuid = routerGuid == null ? null : routerGuid.trim();
    }
}