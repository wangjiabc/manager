package com.voucher.manage2.exception;

import com.voucher.manage2.msg.ExceptionMessage;

/**
 * @author lz
 * @description 全局异常
 * @date 2019/5/15
 */
public class BaseException extends RuntimeException {
    public ExceptionMessage msg;

    public BaseException(ExceptionMessage msg) {
        super(msg.msg);
        this.msg = msg;
    }

    public BaseException(ExceptionMessage msg, Throwable cause) {
        super(msg.msg, cause);
        this.msg = msg;
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
