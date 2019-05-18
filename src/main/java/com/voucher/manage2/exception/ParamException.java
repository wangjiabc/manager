package com.voucher.manage2.exception;

import com.voucher.manage2.msg.Message;

/**
 * @author lz
 * @description 参数错误产生的异常
 * @date 2019/5/15
 */
public class ParamException extends BaseException {
    public ParamException(Message message) {
        super(message);
    }
}
