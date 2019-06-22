package com.voucher.manage2.dto;

import com.voucher.manage2.tkmapper.entity.SysModel;
import com.voucher.manage2.tkmapper.entity.SysRole;

import java.util.List;

/**
 * @author lz
 * @description
 * @date 2019/6/13
 */
public class SysRoleDTO extends SysRole {
    public List<SysModel> models = null;
}
