package com.voucher.manage2.exception;

import com.voucher.manage2.msg.Message;

/**
 * @author lz
 * @description 全局异常
 * @date 2019/5/15
 */
public class BaseException extends RuntimeException {
    public Message msg;

    public BaseException(Message msg) {
        super(msg.msg);
        this.msg = msg;
    }

    public BaseException(Message msg, Throwable cause) {
        super(msg.msg, cause);
        this.msg = msg;
    }
}
