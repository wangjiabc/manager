package com.voucher.manage2.utils;

import com.voucher.manage2.dto.SysUserDTO;
import lombok.extern.slf4j.Slf4j;

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
}
