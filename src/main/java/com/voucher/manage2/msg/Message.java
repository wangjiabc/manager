package com.voucher.manage2.msg;

public enum Message {

    //

    //===================全局消息================

    SUCCESS(100, ""),

    EXCEPTION(999, "未知异常");

    public Integer code;
    public String msg;

    Message(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
