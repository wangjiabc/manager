package com.voucher.manage2.utils;

import com.voucher.manage2.constant.SystemConstant;
import com.voucher.manage2.dto.SysUserDTO;
import com.voucher.manage2.tkmapper.entity.SysRole;
import lombok.extern.slf4j.Slf4j;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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

    public static String getCurrentUserCompanyGuid() {
        return getCurrentUser().getCompanyGuid();
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

    public static double getDouble(Object str) {
        double parseDouble;
        try {
            parseDouble = Double.parseDouble((str == null ? "0" : str.toString()));
            DecimalFormat df = new DecimalFormat("#.00");
            df.setRoundingMode(RoundingMode.FLOOR);
            String format = df.format(parseDouble + 0.005d);
            return Double.parseDouble(format);
        } catch (Exception e) {
            e.printStackTrace();
            return 0d;
        }
    }
}
