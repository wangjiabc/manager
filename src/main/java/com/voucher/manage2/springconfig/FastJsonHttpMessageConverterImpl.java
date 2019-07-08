package com.voucher.manage2.springconfig;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * @author lz
 * @description
 * @date 2019/7/3
 */
public class FastJsonHttpMessageConverterImpl extends FastJsonHttpMessageConverter {
    @Override
    protected boolean supports(Class<?> clazz) {
        if (clazz.equals(byte[].class)) {
            return false;
        }
        return true;
    }
}
