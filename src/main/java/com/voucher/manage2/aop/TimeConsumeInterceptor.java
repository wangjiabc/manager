package com.voucher.manage2.aop;

import com.voucher.manage2.aop.annotation.TimeConsume;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeConsumeInterceptor {
    @Around("within(com..*) && @annotation(timeConsume)")
    public Object doAroundMethod(ProceedingJoinPoint pjd, TimeConsume timeConsume) throws Throwable {
        long startTime = System.currentTimeMillis();//开始时间

        Object[] params = pjd.getArgs();//获取请求参数
        System.out.println("监听到传入参数为:");
        for (Object param : params) {
            System.out.println(param);
        }

        //###################上面代码为方法执行前#####################
        Object result = pjd.proceed();//执行方法，获取返回参数
        //###################下面代码为方法执行后#####################
        System.out.println("返回参数为:" + result);

        long endTime = System.currentTimeMillis();//结束时间
        long excTime = endTime - startTime;
        System.out.println("当前调用方法为" + pjd.getSignature());
        System.out.println("执行时间:" + excTime);
        System.out.println("#######################分隔符##########################");
        return result;

    }
}
