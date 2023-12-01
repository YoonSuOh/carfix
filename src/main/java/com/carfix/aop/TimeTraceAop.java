package com.carfix.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect // 부가 기능 정의 + 어디에 적용(pointcut) => 합친 것
@Slf4j
@Component
public class TimeTraceAop {
    // @Around("execution(* com.carfix..*(..))") // 1. 패키지 범위 -> 어디에 적용?
    @Around("@annotation(TimeTrace)") // 2. 어느 어노테이션이 붙어있을 때 사용할 것인가?
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        // 타겟이 적용하는 메소드 - joinPoint (어느 메소드에 껴넣을 것인가?)
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceedObj = joinPoint.proceed(); // 본래 일 수행

        stopWatch.stop();
        log.info("### 실행 시간(ms)" + stopWatch.getTotalTimeMillis());
        log.info(stopWatch.prettyPrint());

        return proceedObj;
    }
}
