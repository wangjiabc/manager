package com.voucher.manage2.service;

import com.voucher.manage2.dto.SysModelDTO;
import com.voucher.manage2.dto.SysRoleDTO;
import com.voucher.manage2.dto.SysUserDTO;
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

    Integer addUserConditions(List<SysUserCondition> userCondition);

    List<SysUrl> getUrlsLikeCondition(String urlCondition);

    SysModelDTO getUrlsByModel(String modelGuid);

    List<SysModel> getModelsLikeModelName(String modelName);

    SysRoleDTO getModelsByRoleGuid(String RoleGuid);

    List<SysRole> getRolesLikeRoleName(String roleName);

    SysUserDTO getRolesByUserGuid(String userGuid);

    List<SysUserCondition> getUserConditionsByUserGuid(String guid);
}
