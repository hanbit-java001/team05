package com.hanbit.team05.core.exception;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@Order(1)
public class ExceptionAspect {

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object handleException(ProceedingJoinPoint pjp) throws Throwable {
		try{
			return pjp.proceed();
		} catch (Throwable t) {
			MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
			Class returnType = methodSignature.getReturnType();

			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

			if (returnType == String.class) {
				throw t;
			}

			response.setStatus(1500);
			response.setContentType("application/json; charset=utf-8");

			String json = "{\"errorMsg\":\"" + t.getMessage() + "\"}";

			response.getOutputStream().write(json.getBytes());
			response.flushBuffer();
		}
		return null;
	}
}
