package com.voucher.manage2.msg;

public enum ExceptionMessage {

    //

    //===================系统异常================
    IMAGE_FAILED(997, "获取图片失败"),

    //===================文件上传异常================,
    FILE_UPLOAD_FAILED(998, "文件上传失败"),
    //===================全局消息================

    SUCCESS(100, ""),

    EXCEPTION(999, "未知异常");

    private Integer code;
    private String msg;

    ExceptionMessage(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
