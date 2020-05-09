package com.zz.test.javafxmvn.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PaginationAop {
	@Pointcut("@annotation(com.zz.test.javafxmvn.common.aop.PaginationAopAno)")
	public void PaginationAopPoint() {
		
	}
	@After(value = "PaginationAopPoint()")
	public void PaginationAopAfter(JoinPoint point) {
		Object j =point.getClass();
		Object j1 =point .getThis();
		Object j2 =point.getStaticPart();
		System.out.println(1);
	}
	@Before(value = "PaginationAopPoint()")
	public void before(JoinPoint point) {
		Object j =point.getClass();
		Object j1 =point .getThis();
		Object j2 =point.getStaticPart();
		System.out.println(1);
		System.out.println(11);
	}
}
