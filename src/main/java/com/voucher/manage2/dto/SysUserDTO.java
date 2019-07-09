package com.voucher.manage2.dto;

import com.voucher.manage2.tkmapper.entity.SysRole;
import com.voucher.manage2.tkmapper.entity.SysUser;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lz
 * @description
 * @date 2019/6/11
 */
@ToString(callSuper = true)
public class SysUserDTO extends SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<SysRole> roles = null;

    private Long lastFreshTime = -1L;

    public Long getLastFreshTime() {
        return lastFreshTime;
    }

    public void setLastFreshTime(Long lastFreshTime) {
        this.lastFreshTime = lastFreshTime;
    }
}
