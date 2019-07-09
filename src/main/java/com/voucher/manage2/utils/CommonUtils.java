package com.voucher.manage2.utils;

import com.voucher.manage2.constant.SystemConstant;
import com.voucher.manage2.dto.SysUserDTO;
import com.voucher.manage2.tkmapper.entity.SysRole;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author lz
 * @description
 * @date 2019/5/24
 */
@Slf4j
public class CommonUtils {
    private static final ThreadLocal<SysUserDTO> threadLocalUsers = new ThreadLocal();

    public static SysUserDTO getCurrentUser() {
        return threadLocalUsers.get();
    }

    public static String getCurrentUserGuid() {
        return getCurrentUser().getGuid();
    }

    public static String getCurrentUserName() {
        return getCurrentUser().getName();
    }

    public static void setUser(SysUserDTO user) {
        threadLocalUsers.set(user);
    }

    public static boolean isSuperAdmin() {

        List<SysRole> roles = getCurrentUser().roles;
        for (SysRole role : roles) {
            if (role.getGuid().equals(SystemConstant.SYSTEM_ROLE_GUID)) {
                return true;
            }
        }
        return false;
    }
}
