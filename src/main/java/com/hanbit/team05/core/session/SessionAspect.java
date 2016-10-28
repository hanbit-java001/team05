package com.hanbit.team05.core.session;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class SessionAspect {

	@Around("@annotation(com.hanbit.team05.core.session.LoginRequired)")
	public Object checkLogin(ProceedingJoinPoint pjp) throws Throwable {
		Session session = SessionHelper.getSession();

		if (session.isLoggedIn()) {
			return pjp.proceed();
		}

		MethodSignature methodSignature = (MethodSignature) pjp.proceed();
		Class returnType = methodSignature.getReturnType();

		if (returnType == String.class) {
			return "login";

		}
		throw new RuntimeException("로그인이 필요합니다.");
	}
}
