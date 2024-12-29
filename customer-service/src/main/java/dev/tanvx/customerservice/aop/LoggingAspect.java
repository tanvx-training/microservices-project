package dev.tanvx.customerservice.aop;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* dev.tanvx..*(..))") // Adjust the package as needed
    public Object logMethodCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Log method entry
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("BEGIN: {}.{}() with arguments = {}", className, methodName, Arrays.toString(args));

        Object result;
        try {
            // Proceed with the method execution
            result = joinPoint.proceed();
        } catch (Exception e) {
            // Log exception if method throws an error
            logger.error("EXCEPTION in {}.{}(): {}", className, methodName, e.getMessage(), e);
            throw e;
        }

        // Log method exit
        long duration = System.currentTimeMillis() - startTime;
        logger.info("EXIT: {}.{}() with result = {} in {}ms", className, methodName, result, duration);

        return result;
    }
}

