package com.voucher.manage2.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.voucher.manage2.constant.SystemConstant;
import com.voucher.manage2.dto.SysUserDTO;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.service.SysService;
import com.voucher.manage2.service.UserService;
import com.voucher.manage2.tkmapper.entity.SysRole;
import com.voucher.manage2.tkmapper.entity.SysUser;
import com.voucher.manage2.tkmapper.mapper.SysUserMapper;
import com.voucher.manage2.utils.CommonUtils;
import com.voucher.manage2.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lz
 * @description
 * @date 2019/6/11
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SysService sysService;

    @Override
    public List<String> userAllPermission(SysUser sysUser) {
        return sysUserMapper.userAllPermission(sysUser.getGuid());
    }

    @Override
    public boolean hasPermission(SysUserDTO sysUser, String url) {
        if (CommonUtils.isSuperAdmin()) {
            return true;
        }
        return userService.userAllPermission(sysUser).contains(url);
    }


    @Override
    public SysUser regist(SysUser sysUser) {

        List<SysUser> userByAccountName = userService.getUserByAccountName(sysUser);
        if (ObjectUtils.isNotEmpty(userByAccountName)) {
            throw BaseException.getDefault("用户名已存在!");
        }
        String userUuid = IdUtil.simpleUUID();
        sysUser.setGuid(userUuid);
        String salt = SecureUtil.md5(userUuid);
        sysUser.setSalt(salt);
        if (ObjectUtils.isEmpty(sysUser.getPassword())) {
            sysUser.setPassword("123456");
        }
        if (ObjectUtils.isEmpty(sysUser.getCompanyGuid())) {
            sysUser.setCompanyGuid(CommonUtils.getCurrentUserCompanyGuid());
        }
        sysUser.setPassword(SecureUtil.md5(sysUser.getPassword() + salt));
        sysUserMapper.insertSelective(sysUser);
        return sysUser;
    }

    private List<SysUser> getUserByAccountName(SysUser sysUser) {
        Weekend<SysUser> valid = new Weekend<>(SysUser.class);
        valid.weekendCriteria().andEqualTo(SysUser::getAccountName, sysUser.getAccountName());
        return sysUserMapper.selectByExample(valid);
    }

    @Override
    public SysUserDTO login(SysUser loginSysUser) {

        List<SysUser> userByAccountName = userService.getUserByAccountName(loginSysUser);
        if (ObjectUtils.isEmpty(userByAccountName)) {
            throw BaseException.getDefault("用户名不存在!");
        }

        SysUser sysUser = userByAccountName.get(0);
        if (!sysUser.getPassword().equals(SecureUtil.md5(loginSysUser.getPassword() + sysUser.getSalt()))) {
            throw BaseException.getDefault("用户名或密码错误!");
        }
        SysUserDTO sysUserDTO = new SysUserDTO();
        BeanUtils.copyProperties(sysUser, sysUserDTO);
        sysService.setRolesByUserGuid(sysUserDTO);
        return sysUserDTO;
    }

    @Override
    public List<SysUser> getAllUser() {
        Weekend<SysUser> sysUserWeekend = new Weekend<>(SysUser.class);
        WeekendCriteria<SysUser, Object> weekendCriteria = sysUserWeekend.weekendCriteria();
        weekendCriteria.andEqualTo(SysUser::getDel, false);
        if (!CommonUtils.isSuperAdmin()) {
            weekendCriteria.andEqualTo(SysUser::getCompanyGuid, CommonUtils.getCurrentUserCompanyGuid());
        }
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserWeekend);
        if (ObjectUtils.isNotEmpty(sysUsers)) {
            sysUsers.forEach(sysUser -> {
                sysUser.setSalt(null);
                sysUser.setPassword(null);
            });
        }
        return sysUsers;
    }

    @Override
    public Integer updateUser(SysUser sysUser) {
        String userGuid = sysUser.getGuid();
        sysUser.setPassword(null);
        sysUser.setAccountName(null);
        sysUser.setSalt(null);
        sysUser.setGuid(null);
        Weekend<SysUser> sysUserWeekend = new Weekend<>(SysUser.class);
        sysUserWeekend.weekendCriteria().andEqualTo(SysUser::getGuid, userGuid);
        return sysUserMapper.updateByExampleSelective(sysUser, sysUserWeekend);
    }

    @Override
    public Integer updatePassWord(SysUser sysUser, String newPassword) {
        String userGuid = CommonUtils.getCurrentUserGuid();
        String salt = SecureUtil.md5(userGuid);
        String oldPassword = SecureUtil.md5(sysUser.getPassword() + salt);
        SysUser conditionUser = new SysUser();
        conditionUser.setPassword(SecureUtil.md5(newPassword + salt));
        Weekend<SysUser> sysUserWeekend = new Weekend<>(SysUser.class);
        sysUserWeekend.weekendCriteria().andEqualTo(SysUser::getGuid, userGuid).andEqualTo(SysUser::getPassword, oldPassword);
        return sysUserMapper.updateByExampleSelective(conditionUser, sysUserWeekend);
    }
}
