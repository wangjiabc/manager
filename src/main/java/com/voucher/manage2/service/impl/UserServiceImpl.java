package com.voucher.manage2.service.impl;

import com.voucher.manage.daoSQL.annotations.PrimaryKey;
import com.voucher.manage2.service.UserService;
import com.voucher.manage2.tkmapper.entity.SysUser;
import com.voucher.manage2.tkmapper.mapper.SysUrlMapper;
import com.voucher.manage2.tkmapper.mapper.SysUserMapper;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<String> userAllPermission(SysUser sysUser) {
        return sysUserMapper.userAllPermission(sysUser.getGuid());
    }

    @Override
    public boolean hasPermission(SysUser sysUser, String url) {
        return userService.userAllPermission(sysUser).contains(url);
    }
}
