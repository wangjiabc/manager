package com.voucher.manage2.msg;

/**
 * @Author lz
 * @Description: 返回前台的实体
 * @Date: 2019/5/15 9:57
 **/
public class MessageBean {

    /**
     * 响应代码，100为正常
     */
    protected Integer code;
    protected Object body;
    /**
     * 后台响应具体信息
     */
    protected String msg;

    protected MessageBean(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    protected MessageBean(Integer code, String msg, Object body) {
        this.code = code;
        this.body = body;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MessageBean [code=" + code + ", body=" + body + ", msg=" + msg + "]";
    }

    public static MessageBean getMessageBean(Message msg) {
        return new MessageBean(msg.code, msg.msg);
    }

    public static MessageBean getMessageBean(Message msg, Object body) {
        return new MessageBean(msg.code, msg.msg, body);
    }

    public static MessageBean getMessageBean(Integer code, String msg) {
        return new MessageBean(code, msg);
    }

    public static MessageBean getMessageBean(Integer code, String msg, Object body) {
        return new MessageBean(code, msg, body);
    }
}
