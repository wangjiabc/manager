package com.voucher.manage2.service.impl;

import com.voucher.manage2.dto.SysModelDTO;
import com.voucher.manage2.dto.SysRoleDTO;
import com.voucher.manage2.dto.SysUserDTO;
import com.voucher.manage2.service.SysService;
import com.voucher.manage2.tkmapper.entity.*;
import com.voucher.manage2.tkmapper.mapper.*;
import com.voucher.manage2.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public Integer updateModelUrls(List<SysModelUrl> modelUrls, List<SysModelUrl> delModelUrls) {
        Integer success = 0;
        if (ObjectUtils.isNotEmpty(delModelUrls)) {
            for (SysModelUrl delModelUrl : delModelUrls) {
                sysModelUrlMapper.delete(delModelUrl);
            }
        }
        if (ObjectUtils.isNotEmpty(modelUrls)) {
            success = sysModelUrlMapper.insertList(modelUrls);
        }
        return success;
    }

    @Override
    public Integer addRole(SysRole role) {
        return sysRoleMapper.insert(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateRoleModels(List<SysRoleModel> addModels, List<SysRoleModel> delModels) {
        Integer success = 0;
        if (ObjectUtils.isNotEmpty(delModels)) {
            for (SysRoleModel delModel : delModels) {
                sysRoleModelMapper.delete(delModel);
            }
        }
        if (ObjectUtils.isNotEmpty(addModels)) {
            success = sysRoleModelMapper.insertList(addModels);
        }
        return success;
    }

    @Override
    public Integer addRoleMenus(List<SysRoleMenu> roleMenus) {
        return sysRoleMenuMapper.insertList(roleMenus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateUserRoles(List<SysUserRole> userRoles, List<SysUserRole> delUserRoles) {
        Integer success = 0;
        if (ObjectUtils.isNotEmpty(delUserRoles)) {
            for (SysUserRole delModel : delUserRoles) {
                sysUserRoleMapper.delete(delModel);
            }
        }
        if (ObjectUtils.isNotEmpty(userRoles)) {
            success = sysUserRoleMapper.insertList(userRoles);
        }
        return success;
    }

    @Override
    public Integer addUserConditions(List<SysUserCondition> userCondition) {
        return sysUserConditionMapper.insertList(userCondition);
    }

    @Override
    public List<SysUrl> getUrlsLikeCondition(String urlCondition) {
        Weekend<SysUrl> sysUrlWeekend = new Weekend<>(SysUrl.class);
        if (ObjectUtils.isNotEmpty(urlCondition)) {
            sysUrlWeekend.weekendCriteria().orLike(SysUrl::getName, urlCondition)
                    .orLike(SysUrl::getUrl, urlCondition).orLike(SysUrl::getDescription, urlCondition);
        }
        return sysUrlMapper.selectByExample(sysUrlWeekend);
    }

    @Override
    public SysModelDTO getUrlsByModel(String modelGuid) {
        SysModelDTO sysModelDTO = new SysModelDTO();
        sysModelDTO.setGuid(modelGuid);
        sysModelDTO.urls = sysUrlMapper.getUrlsByModel(modelGuid);
        return sysModelDTO;
    }


    @Override
    public List<SysModel> getModelsLikeModelName(String modelName) {
        Weekend<SysModel> sysModelWeekend = new Weekend<>(SysModel.class);
        if (ObjectUtils.isNotEmpty(modelName)) {
            sysModelWeekend.weekendCriteria().orLike(SysModel::getName, modelName);
        }
        return sysModelMapper.selectByExample(sysModelWeekend);
    }

    @Override
    public SysRoleDTO getModelsByRoleGuid(String roleGuid) {
        SysRoleDTO sysRoleDTO = new SysRoleDTO();
        sysRoleDTO.setGuid(roleGuid);
        sysRoleDTO.models = sysModelMapper.getModelsByRoleGuid(roleGuid);
        return sysRoleDTO;
    }

    @Override
    public List<SysRole> getRolesLikeRoleName(String roleName) {
        Weekend<SysRole> sysModelWeekend = new Weekend<>(SysRole.class);
        if (ObjectUtils.isNotEmpty(roleName)) {
            sysModelWeekend.weekendCriteria().orLike(SysRole::getName, roleName);
        }
        return sysRoleMapper.selectByExample(sysModelWeekend);
    }

    @Override
    public SysUserDTO setRolesByUserGuid(SysUserDTO sysUserDTO) {
        String userGuid = sysUserDTO.getGuid();
        sysUserDTO.roles = sysRoleMapper.getRolesByUserGuid(userGuid);
        return sysUserDTO;
    }

    @Override
    public List<SysUserCondition> getUserConditionsByUserGuid(String guid) {
        Weekend<SysUserCondition> sysUserConditionWeekend = new Weekend<>(SysUserCondition.class);
        sysUserConditionWeekend.weekendCriteria().andEqualTo(SysUserCondition::getUserGuid, guid);
        return sysUserConditionMapper.selectByExample(sysUserConditionWeekend);
    }

    @Override
    public Integer updateUserConditions(SysUserCondition sysUserCondition) {
        Weekend<SysUserCondition> sysUserConditionWeekend = new Weekend<>(SysUserCondition.class);
        sysUserConditionWeekend.weekendCriteria().andEqualTo(SysUserCondition::getUserGuid, sysUserCondition.getUserGuid());
        sysUserCondition.setUserGuid(null);
        return sysUserConditionMapper.updateByExampleSelective(sysUserCondition, sysUserConditionWeekend);
    }

    @Override
    public List<Router> getAllRouter() {
        return null;
    }

    @Override
    public List<Router> getRouterByUser(String userGuid) {
        return null;
    }
}
