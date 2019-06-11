package com.voucher.manage2.exception;

import com.voucher.manage2.msg.ExceptionMessage;

/**
 * @author lz
 * @description 全局异常
 * @date 2019/5/15
 */
public class BaseException extends RuntimeException {
    private ExceptionMessage msg;

    public BaseException(ExceptionMessage msg) {
        super(msg.getMsg());
        this.msg = msg;
    }

    public BaseException(ExceptionMessage msg, Throwable cause) {
        super(msg.getMsg(), cause);
        this.msg = msg;
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BaseException(String msg) {
        super(msg);
    }

    public static BaseException getDefault(Throwable e) {
        return new BaseException(ExceptionMessage.EXCEPTION, e);
    }

    public static BaseException getDefault() {
        return new BaseException(ExceptionMessage.EXCEPTION);
    }

    public static BaseException getDefault(String msg) {
        return new BaseException(msg);
    }

    public ExceptionMessage getMsg() {
        return msg;
    }
}
