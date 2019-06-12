package com.voucher.manage2.service.impl;

import com.voucher.manage2.service.SysService;
import com.voucher.manage2.tkmapper.entity.*;
import com.voucher.manage2.tkmapper.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

/**
 * @author lz
 * @description
 * @date 2019/6/10
 */
@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysUrlMapper sysUrlMapper;
    @Autowired
    private SysModelMapper sysModelMapper;
    @Autowired
    private SysModelUrlMapper sysModelUrlMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleModelMapper sysRoleModelMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserConditionMapper sysUserConditionMapper;

    @Override
    public Integer addUrls(List<SysUrl> urls) {
        return sysUrlMapper.insertList(urls);
    }

    @Override
    public Integer addModel(SysModel model) {
        return sysModelMapper.insert(model);
    }

    @Override
    public Integer addModelUrls(List<SysModelUrl> modelUrls) {
        return sysModelUrlMapper.insertList(modelUrls);
    }

    @Override
    public Integer addRole(SysRole role) {
        return sysRoleMapper.insert(role);
    }

    @Override
    public Integer addRoleModels(List<SysRoleModel> roleModels) {
        return sysRoleModelMapper.insertList(roleModels);
    }

    @Override
    public Integer addRoleMenus(List<SysRoleMenu> roleMenus) {
        return sysRoleMenuMapper.insertList(roleMenus);
    }

    @Override
    public Integer addUserRoles(List<SysUserRole> userRoles) {
        return sysUserRoleMapper.insertList(userRoles);
    }

    @Override
    public Integer addUserConditions(List<SysUserCondition> userCondition) {
        return sysUserConditionMapper.insertList(userCondition);
    }

    @Override
    public List<SysUrl> getUrls(String urlCondition) {
        Weekend<SysUrl> sysUrlWeekend = new Weekend<>(SysUrl.class);
        sysUrlWeekend.weekendCriteria().andLike(SysUrl::getName, urlCondition)
                .andLike(SysUrl::getUrl, urlCondition);
        return sysUrlMapper.selectByExample(sysUrlWeekend);
    }

    @Override
    public List<SysModel> getModelsLikeModelName(String modelName) {
        Weekend<SysModel> sysModelWeekend = new Weekend<>(SysModel.class);
        sysModelWeekend.weekendCriteria().andLike(SysModel::getName, modelName);
        return sysModelMapper.selectByExample(sysModelWeekend);
    }

    @Override
    public List<SysModel> getModelsByRoleGuid(String RoleGuid) {
        return null;
    }

    @Override
    public List<SysRole> getRolesLikeRoleName(String roleName) {
        Weekend<SysRole> sysModelWeekend = new Weekend<>(SysRole.class);
        sysModelWeekend.weekendCriteria().andLike(SysRole::getName, roleName);
        return sysRoleMapper.selectByExample(sysModelWeekend);
    }

    @Override
    public List<SysRole> getRolesByUserGuid(String userGuid) {
        return null;
    }

    @Override
    public List<SysUserCondition> getUserConditionsByUserGuid(String guid) {
        Weekend<SysUserCondition> sysUserConditionWeekend = new Weekend<>(SysUserCondition.class);
        sysUserConditionWeekend.weekendCriteria().andEqualTo(SysUserCondition::getUserGuid, guid);
        return sysUserConditionMapper.selectByExample(sysUserConditionWeekend);
    }
}
