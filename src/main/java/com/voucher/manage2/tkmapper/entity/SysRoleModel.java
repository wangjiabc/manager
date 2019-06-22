package com.voucher.manage2.tkmapper.entity;

import javax.persistence.*;

@Table(name = "[sys_role_model]")
public class SysRoleModel {
    @Column(name = "[model_guid]")
    private String modelGuid;

    @Column(name = "[role_guid]")
    private String roleGuid;

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
}