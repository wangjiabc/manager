package com.voucher.manage2.msg;

/**
 * @author lz
 * @description 用于返回错误信息
 * @date 2019/5/15
 */
public class ErrorMessageBean extends MessageBean {

    private ErrorMessageBean(Integer code, String msg) {
        super(code, msg);
        this.code = code;
        this.msg = msg;
    }

    private ErrorMessageBean(Integer code, String msg, Object body) {
        super(code, msg, body);
        this.code = code;
        this.body = body;
        this.msg = msg;
    }

    public static ErrorMessageBean getMessageBean(ExceptionMessage msg) {
        return new ErrorMessageBean(msg.code, msg.msg);
    }

    public static ErrorMessageBean getMessageBean(ExceptionMessage msg, Object body) {
        return new ErrorMessageBean(msg.code, msg.msg, body);
    }

    public static ErrorMessageBean getMessageBean(Integer code, String msg) {
        return new ErrorMessageBean(code, msg);
    }

    public static ErrorMessageBean getMessageBean(Integer code, String msg, Object body) {
        return new ErrorMessageBean(code, msg, body);
    }
}
