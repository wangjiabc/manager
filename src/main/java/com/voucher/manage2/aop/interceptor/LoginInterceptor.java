package com.voucher.manage2.aop.interceptor;

import com.voucher.manage2.dto.UserDTO;
import com.voucher.manage2.exception.BaseException;
import com.voucher.manage2.redis.JedisUtil0;
import com.voucher.manage2.service.UserService;
import com.voucher.manage2.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Map;

/**
 * @author lz
 * @description
 * @date 2019/6/11
 */
@Slf4j
@Aspect
@Component
public class LoginInterceptor {
    @Autowired
    private UserService userService;

    //execution(public * com.voucher.manage.controller..*(..)) &&
    @Pointcut("execution(public * com.voucher.manage2.controller..*.*(..))")
    public void controllerPointcut() {
    }

    // 登录登出功能不需要Session验证
    @Pointcut("execution(public * com.voucher.manage2.controller.LoginController.*(..))")
    public void rootPointcut() {
    }

    // 导出功能不需要Session验证
    @Pointcut("execution(public * com.voucher.manage2.controller.ExportController.*(..))")
    public void exportPointcut() {
    }

    // 注册功能不需要Session验证
    @Pointcut("execution(public * com.voucher.manage2.controller.UserController.insertUserInfo(..))")
    public void registerPointcut() {
    }

    @Pointcut("controllerPointcut()")
    //@Pointcut("controllerPointcut()&&(!rootPointcut())&&(!registerPointcut())&&(!exportPointcut())")
    public void sessionTimeOutPointcut() {
    }

    @Around("sessionTimeOutPointcut()")
    public Object sessionTimeOutAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        //Class<?> controller = pjp.getTarget().getClass();
        //Method proxyMethod = ((MethodSignature) pjp.getSignature()).getMethod();
        //log.debug("----------------执行方法-----------------");
        //log.debug("类名：" + controller.getSimpleName() + " 方法名：" + proxyMethod.getName());
        ////类上面controller的值
        //String corName = controller.getAnnotation(Controller.class).value();
        //
        ////真实的方法上才有注解
        //Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        //String name = realMethod.getAnnotation(RequestMapping.class).name();
        //String url = corName + "/" + name;
        //String tokenId;
        Object[] obj = pjp.getArgs();
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
        //        .getRequest();
        //tokenId = request.getHeader("X-Token");
        //String requestURI = request.getRequestURI();
        //try {
        //    if (ObjectUtils.isNotEmpty(tokenId)) {
        //        //TODO 查看redis是否还有token
        //        log.warn("拦截会话超时请求,tokenId:{}, URL:", tokenId, requestURI);
        //        //TODO 获取 user
        //        UserDTO userDTO = JedisUtil0.sgetObject(tokenId);
        //        if (userDTO == null) {
        //            throw BaseException.getDefault("登录已过期,重新登录!");
        //        }
        //        //TODO 判断是否有权限访问接口
        //        if (userService.hasPermission(userDTO, url)) {
        result = pjp.proceed(obj);
        //        } else {
        //            throw BaseException.getDefault("没有权限");
        //        }
        //
        //    } else {
        //        log.warn("非法访问已被拦截URL:{}", requestURI);
        //        throw BaseException.getDefault("非法访问");
        //    }
        //} catch (UndeclaredThrowableException e) {
        //    e.printStackTrace();
        //    throw BaseException.getDefault(e);
        //}
        return result;
    }
}
