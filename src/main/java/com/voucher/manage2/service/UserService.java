package com.voucher.manage2.service;

import com.voucher.manage2.tkmapper.entity.SysUser;

import java.util.List;

/**
 * @author lz
 * @description
 * @date 2019/6/11
 */
public interface UserService {
    List<String> userAllPermission(SysUser sysUser);

    boolean hasPermission(SysUser sysUser, String url);
}
