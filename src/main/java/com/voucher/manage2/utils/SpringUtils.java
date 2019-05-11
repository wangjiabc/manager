package com.voucher.manage2.utils;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author lz
 * @description 关于sping的一些工具
 * @date 2019/5/10
 */
public class SpringUtils {
    /**
     * @Author lz
     * @Description: 根据条件回滚当前事务
     * @param: [condition]
     * @return: {void}
     * @Date: 2019/5/10 9:45
     **/
    public static final void setRollbackOnly(boolean condition) {
        if (condition) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
