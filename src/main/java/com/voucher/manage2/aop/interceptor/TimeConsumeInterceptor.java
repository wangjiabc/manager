package com.voucher.manage2.aop.interceptor;

import com.voucher.manage2.aop.interceptor.annotation.TimeConsume;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeConsumeInterceptor {
    //@Around("within(com..*) && @annotation(timeConsume)")
    @Around("@annotation(timeConsume)")
    public Object doAroundMethod(ProceedingJoinPoint pjd, TimeConsume timeConsume) throws Throwable {
        //开始时间
        long startTime = System.currentTimeMillis();
        //获取请求参数
        Object[] params = pjd.getArgs();
        System.out.println("监听到传入参数为:");
        for (Object param : params) {
            System.out.println(param);
        }

        //###################上面代码为方法执行前#####################
        //执行方法，获取返回参数
        Object result = pjd.proceed();
        //###################下面代码为方法执行后
        System.out.println("返回参数为:" + result);
        //结束时间
        long endTime = System.currentTimeMillis();
        long excTime = endTime - startTime;
        System.out.println("当前调用方法为" + pjd.getSignature());
        System.out.println("执行时间:" + excTime);
        System.out.println("#######################分隔符##########################");
        return result;

    }
}
