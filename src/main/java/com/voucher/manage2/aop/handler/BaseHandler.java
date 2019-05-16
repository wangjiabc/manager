package com.voucher.manage2.aop.handler;

import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.msg.ErrorMessageBean;
import com.voucher.manage2.msg.Message;
import com.voucher.manage2.msg.MessageBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author lz
 * @description 对controller做一些增强
 * @date 2019/5/15
 */
@ControllerAdvice
public class BaseHandler implements ResponseBodyAdvice<Object> {


    /**
     * 处理所有不可知异常
     *
     * @param e 异常
     * @return json结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorMessageBean handleException(Exception e) {
        // 打印异常堆栈信息
        //LOG.error(e.getMessage(), e);
        e.printStackTrace();
        return ErrorMessageBean.getMessageBean(Message.EXCEPTION);
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ErrorMessageBean handleBaseException(BaseException e) {
        // 打印异常堆栈信息
        //LOG.error(e.getMessage(), e);
        e.printStackTrace();
        return ErrorMessageBean.getMessageBean(e.msg);
    }

    //是否对方法进行增强判定
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        //body是返回值
        //HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        Class<?> parameterType = returnType.getParameterType();
        if (parameterType.getName().equals("com.voucher.manage2.msg.ErrorMessageBean"))
            return body;
        //parameterType.
        System.out.println("++++++++++" + parameterType);
        //if (returnType.getParameterType() instanceof ErrorMessageBean)
        // TODO Auto-generated method stub
        return MessageBean.getMessageBean(Message.SUCCESS, body);
    }
}
