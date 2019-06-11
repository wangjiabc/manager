package com.voucher.manage2.service.impl;

import com.voucher.manage2.service.SysService;
import com.voucher.manage2.tkmapper.entity.*;
import com.voucher.manage2.tkmapper.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Integer addUserConditions(SysUserCondition userCondition) {
        return sysUserConditionMapper.insert(userCondition);
    }
}
