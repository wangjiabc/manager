package com.voucher.manage2.service;

import com.voucher.manage2.tkmapper.entity.*;

import java.util.List;

public interface SysService {

    Integer addUrls(List<SysUrl> urls);

    Integer addModel(SysModel model);

    Integer addModelUrls(List<SysModelUrl> modelUrls);

    Integer addRole(SysRole role);

    Integer addRoleModels(List<SysRoleModel> roleModels);

    Integer addRoleMenus(List<SysRoleMenu> roleMenus);

    Integer addUserRoles(List<SysUserRole> userRoles);

    Integer addUserConditions(SysUserCondition userCondition);
}
