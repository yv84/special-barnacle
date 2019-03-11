package me.yv84.specialbarnacle.fuzzypancake.web.config.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * Aspect for logging execution of service and repository Spring components.
 */
@Aspect
public class LoggingAspect {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut that matches LogTime annotation.
     */
    @Pointcut("execution(@me.yv84.specialbarnacle.fuzzypancake.web.config.logging.LogTime * *(..))")
    public void logTimePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all controller in the application's main packages.
     */
    @Pointcut("within(me.yv84.specialbarnacle.fuzzypancake.web.controller..*)")
    public void restControllerPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all ControllerAdvice in the application's main packages.
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.ControllerAdvice *)")
    public void controllerAdvicePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }


    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "restControllerPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        LOG.error("Exception in {}.{}() with argument[s] = {}, cause = \'{}\'",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()), e.getCause() != null ? e.getCause() : "NULL");
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("restControllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        try {
            Object result = joinPoint.proceed();
            LOG.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            return result;
        } catch (IllegalArgumentException e) {
            LOG.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }

    /**
     * Advice that log time when a method is exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("logTimePointcut()")
    public Object logTimeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            return joinPoint.proceed();
        } finally {
            watch.stop();
            LOG.info("Time: {}.{}() is processed within {} millis", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), watch.getLastTaskTimeMillis());
        }
    }

    /**
     * Advice that logs when a method in ControllerAdvice is exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("controllerAdvicePointcut()")
    public Object logExceptionTranslator(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        LOG.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), result);
        return result;
    }
}
