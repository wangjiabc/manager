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
import com.voucher.manage2.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;

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
        List<SysRole> roles = sysUser.roles;
        for (SysRole role : roles) {
            if (role.getGuid().equals(SystemConstant.SYSTEM_ROLE_GUID)) {
                return true;
            }
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
        Weekend<SysUser> sysUserWeekend = new Weekend<SysUser>(SysUser.class);
        sysUserWeekend.weekendCriteria().andEqualTo(SysUser::getDel, false);
        List<SysUser> sysUsers = sysUserMapper.selectAll();
        if (ObjectUtils.isNotEmpty(sysUsers)) {
            sysUsers.forEach(sysUser -> {
                sysUser.setSalt(null);
                sysUser.setPassword(null);
            });
        }
        return sysUsers;
    }
}
